package com.coelle_online;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/** Some basic tests regarding @link Beef. */
public class BeefTest {
    private static final long TIME_TO_PASS_FOR_EXPIRY = 100L;

    @SuppressWarnings("NestedMethodCall")
    @Test
    public final void shouldBePastItsPrimeWhenExpiryDateIsPast() throws Exception {
        final Date now = new Date();
        final Clock clock = new SystemClock();

        final Beef beef = new Beef(clock, now);

        Thread.sleep(TIME_TO_PASS_FOR_EXPIRY * 2L); // Sleep? Bleh...

        final boolean actual = beef.isPastItsPrime();
        Assert.assertThat(actual, Is.is(true));
    }

    @SuppressWarnings("NestedMethodCall")
    @Test
    public final void shouldBePastItsPrimeWhenExpiryDateIsPastWithoutSleeping() throws Exception {
        final Date now = new Date();
        final ControlledClock clock = new ControlledClock();

        final Beef beef = new Beef(clock, now);
        clock.forwardTimeInMillis(TIME_TO_PASS_FOR_EXPIRY);

        final boolean actual = beef.isPastItsPrime();
        Assert.assertThat(actual, Is.is(true));
    }
}
