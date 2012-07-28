package com.coelle_online;

import java.util.Date;

/**
 * Creates a Clock with the timestamp of NOW. Every call to {@link #now} will return the same timestamp until the time
 * is modified by calling {@link #forwardTimeInMillis(long)}.
 */
public class ControlledClock implements Clock {
    private Date now;

    public ControlledClock() {
        now = new Date();
    }

    /**
     * @return Returns the same timestamp until the internal timestamp is modified by {@link
     *         #forwardTimeInMillis(long)}.
     */
    @Override
    public final Date now() {
        final long nowTime = now.getTime();
        return new Date(nowTime);
    }

    public void forwardTimeInMillis(final long milliseconds) {
        final long nowTime = now.getTime();
        now = new Date(nowTime + milliseconds);
    }

    @Override
    public final String toString() {
        return String.format("ControlledClock{now=%s}", now);
    }
}
