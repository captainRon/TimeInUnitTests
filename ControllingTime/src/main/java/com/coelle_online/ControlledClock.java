package com.coelle_online;

import org.joda.time.DateTime;

/**
 * Creates a Clock with the timestamp of NOW. Every call to {@link #now} will return the same timestamp until the time
 * is modified by calling {@link #forwardTimeInMillis(long)}.
 */
public class ControlledClock implements Clock {
    private DateTime now;

    public ControlledClock() {
        now = new DateTime();
    }

    /**
     * @return Returns the same timestamp until the internal timestamp is modified by {@link
     *         #forwardTimeInMillis(long)}.
     */
    @Override
    public final DateTime now() {
        return now.toDateTime();
    }

    public void forwardTimeInMillis(final long milliseconds) {
        now = now.plus(milliseconds);
    }

    @Override
    public final String toString() {
        return String.format("ControlledClock{now=%s}", now);
    }
}
