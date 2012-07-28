package com.coelle_online;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/** Description User: coellen Date: 28.07.12 Time: 16:07 */
public class BeefTest {
    @Test
    public void shouldBePastItsPrimeWhenExpiryDateIsPast() throws Exception {
        int timeToPassForExpiry = 100;

        Beef beef = new Beef(new DateTime().plus(timeToPassForExpiry));

        Thread.sleep(timeToPassForExpiry * 2); // Sleep? Bleh...

        assertTrue(beef.isPastItsPrime());
    }
}
