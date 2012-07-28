package com.coelle_online;

import org.joda.time.DateTime;

public class SystemClock implements Clock {
    @Override
    public final DateTime now() {
        return new DateTime();
    }

    @Override
    public final String toString() {
        return this.getClass().getSimpleName();
    }
}
