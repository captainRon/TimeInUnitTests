package com.coelle_online;

import sun.misc.Signal;
import sun.misc.SignalHandler;

@SuppressWarnings("UseOfSunClasses")
public class RunningProgram implements SignalHandler {
    private boolean running = true;

    @Override
    public final void handle(final Signal signal) {
        // Program terminated by a signal
        running = false;
    }

    public static void main(String[] args) {
        final RunningProgram program = new RunningProgram();

        Signal.handle(new Signal("TERM"), program);
        Signal.handle(new Signal("INT"), program);
        program.start();
    }

    private void start() {
        running = true;
        while (running) {
            // do some work (that we want to unit test)
            // it changes about ten fields depending on what condition
            // gets executed
        }
    }
}
