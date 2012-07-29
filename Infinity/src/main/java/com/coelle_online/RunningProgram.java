package com.coelle_online;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import sun.misc.Signal;
import sun.misc.SignalHandler;

@SuppressWarnings("UseOfSunClasses")
public class RunningProgram {
    /*
     * It bothered me that the main program had the shutdown hook.
     * That behaviour definitely felt strongly related to the
     * RunningCondition. I felt it was a good thing to move it to
     * the that class.
     *
     * Note that it is now the RunningCondition that now implements
     * the SignalHandler interface (that we are using to register
     * with the Signal
     *
     * [http://www.thekua.com/atwork/2009/02/controlling-time-how-to-deal-with-infinity/]
     */
    private static class RunningCondition implements SignalHandler  {
        private boolean running = true;

        public boolean shouldContinue() {
            return running;
        }

        public void handle(Signal signal) {
            running = false;
        }
    }

    private RunningCondition condition = new RunningCondition();

    public static void main(String[] args) {
        final RunningProgram program = new RunningProgram();

        Signal.handle(new Signal("TERM"), program.condition);
        Signal.handle(new Signal("INT"), program.condition);
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
