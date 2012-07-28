package com.coelle_online;

import java.util.Date;

public class SystemClock implements Clock {
    @Override
    public final Date now() {
        return new Date();
    }

    @Override
    public final String toString() {
        return this.getClass().getSimpleName();
    }
}
