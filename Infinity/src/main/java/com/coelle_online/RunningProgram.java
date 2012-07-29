package com.coelle_online;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import sun.misc.Signal;
import sun.misc.SignalHandler;

@SuppressWarnings("UseOfSunClasses")
public class RunningProgram {
    /*
     * The difficulty with this class still exists. We cannot modify
     * the RunningCondition of this program since it creates one for
     * itself. Since I prefer Constructor Based Dependency Injection,
     * I’m going to apply Introduce Parameter to Constructor, moving
     * the field declaration to the constructor itself.
     *
     *
     *
     * [http://www.thekua.com/atwork/2009/02/controlling-time-how-to-deal-with-infinity/]
     */
    private static class RunningCondition implements SignalHandler  {
        private boolean running = true;

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

    @SuppressWarnings("InstanceVariableOfConcreteClass")
    private final RunningCondition condition;

    @SuppressWarnings("MethodParameterOfConcreteClass")
    public RunningProgram(final RunningCondition runningCondition) {
        this.condition = runningCondition;
    }

    @SuppressWarnings("MethodCanBeVariableArityMethod")
    public static void main(final String[] args) {
        final RunningProgram program = new RunningProgram(new RunningCondition());

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
