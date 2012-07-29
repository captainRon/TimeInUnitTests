package com.coelle_online;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import sun.misc.Signal;
import sun.misc.SignalHandler;

@SuppressWarnings("UseOfSunClasses")
public class RunningProgram {
    /*
     * Note that we are still bound to the implementation of the
     * specific RunLikeADaemonStrategy, so it’s time to apply Extract
     * Interface, and understand what role that RunLikeADaemonStrategy
     * has. We chose the name, RunStrategy for the role name, and
     * to help keep the names more aligned, we ended up renaming
     * RunLikeADaemonStrategy to RunLikeADaemonStrategy.
     *
     * The best thing is that our RunningProgram no longer needs to
     * know what happens if a terminate or interrupt signal is sent to
     * the program. With simply a dependency on the RunStrategy we can
     * know inject a fixed run strategy for tests that we ended up
     * calling a RunNumberOfTimesStrategy. We also promoted the
     * specific RunLikeADaemonStrategy to a full class (not an inner
     * class).
     *
     * [http://www.thekua.com/atwork/2009/02/controlling-time-how-to-deal-with-infinity/]
     */
    private interface RunStrategy {
        boolean shouldContinue();
    }

    private static class RunLikeADaemonStrategy implements SignalHandler, RunStrategy {
        private boolean running = true;

        @Override
        public final boolean shouldContinue() {
            return running;
        }

        @Override
        public final void handle(final Signal signal) {
            running = false;
        }

        @Override
        public final String toString() {
            //noinspection ChainedMethodCall
            return new ToStringBuilder(this).
                    append("running", running).
                    toString();
        }
    }

    private final RunStrategy condition;

    public RunningProgram(final RunStrategy runningCondition) {
        this.condition = runningCondition;
    }

    @SuppressWarnings("MethodCanBeVariableArityMethod")
    public static void main(final String[] args) {
        final RunLikeADaemonStrategy strategy = new RunLikeADaemonStrategy();
        final RunningProgram program = new RunningProgram(strategy);

        Signal.handle(new Signal("TERM"), strategy); //NON-NLS
        Signal.handle(new Signal("INT"), strategy); //NON-NLS
        program.start();
    }

    private void start() {
        while (condition.shouldContinue()) {
            // do some work (that we want to unit test)
            // it changes about ten fields depending on what condition
            // gets executed
        }
    }

    @Override
    public final String toString() {
        //noinspection ChainedMethodCall
        return new ToStringBuilder(this).
                append("condition", condition).
                toString();
    }

    @Override
    public final int hashCode() {
        //noinspection ChainedMethodCall
        return new HashCodeBuilder().append(condition).toHashCode();
    }

    @Override
    public final boolean equals(final Object obj) {
        final boolean retVal;
        if ((obj != null) && (obj.getClass() == this.getClass())) {
            if (this == obj) {
                retVal = true;
            } else {
                @SuppressWarnings("CastToConcreteClass")
                final RunningProgram beef = (RunningProgram) obj;

                //noinspection ChainedMethodCall
                retVal = new EqualsBuilder()
                        .append(this.condition, beef.condition)
                        .isEquals();
            }
        } else {
            retVal = false;
        }
        return retVal;
    }

}
