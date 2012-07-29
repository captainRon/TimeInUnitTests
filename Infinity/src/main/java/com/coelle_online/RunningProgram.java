package com.coelle_online;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import sun.misc.Signal;
import sun.misc.SignalHandler;

@SuppressWarnings("UseOfSunClasses")
public class RunningProgram implements SignalHandler {
    /*
     * Our first task, was to remove direct access to the running flag
     * since the class modified it in two places. Sprout an inner class,
     * and simply delegate to getters and setters.
     *
     * Moving the running flag to a separate class gives us a number of
     * benefits. It lets us hide the implementation of how we handle
     * running, and puts us down the road of clearly teasing apart the
     * overloaded responsibilities.
     * [http://www.thekua.com/atwork/2009/02/controlling-time-how-to-deal-with-infinity/]
     */
    private static class RunningCondition {
        private boolean running = true;

        public boolean shouldContinue() {
            return running;
        }

        public void stop() {
            running = false;
        }
    }

    private RunningCondition condition = new RunningCondition();

    @Override
    public final void handle(final Signal signal) {
        // Program terminated by a signal
        condition.stop();
    }

    public static void main(String[] args) {
        final RunningProgram program = new RunningProgram();

        Signal.handle(new Signal("TERM"), program);
        Signal.handle(new Signal("INT"), program);
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
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RunningProgram other = (RunningProgram) obj;
        //noinspection ChainedMethodCall
        return new EqualsBuilder().append(this.condition, other.condition).isEquals();
    }

}
