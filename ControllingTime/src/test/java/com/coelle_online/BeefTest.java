package com.coelle_online;

import org.hamcrest.core.Is;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

/**
 * Some basic tests regarding @link Beef.
 */
public class BeefTest {
    private static final long TIME_TO_PASS_FOR_EXPIRY = 100L;

    @SuppressWarnings("NestedMethodCall")
    @Test
    public final void shouldBePastItsPrimeWhenExpiryDateIsPast() throws Exception {
        final DateTime now = new DateTime();
        final DateTime stamp = now.plus(TIME_TO_PASS_FOR_EXPIRY);
        final Clock clock = new SystemClock();

        final Beef beef = new Beef(clock, stamp);

        Thread.sleep(TIME_TO_PASS_FOR_EXPIRY * 2L); // Sleep? Bleh...

        final boolean actual = beef.isPastItsPrime();
        Assert.assertThat(actual, Is.is(true));
    }

    @SuppressWarnings("NestedMethodCall")
    @Test
    public final void shouldBePastItsPrimeWhenExpiryDateIsPastWithoutSleeping() throws Exception {
        final DateTime now = new DateTime();
        final DateTime stamp = now.plus(TIME_TO_PASS_FOR_EXPIRY);
        final ControlledClock clock = new ControlledClock();

        final Beef beef = new Beef(clock, stamp);
        clock.forwardTimeInMillis(TIME_TO_PASS_FOR_EXPIRY);

        final boolean actual = beef.isPastItsPrime();
        Assert.assertThat(actual, Is.is(true));
    }
}
