package com.coelle_online;

import com.gargoylesoftware.base.testing.EqualsTester;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * This class contains the tests for {@link Beef#equals}, {@link Beef#hashCode()}
 * and {@link Beef#toString()}
 */
public class BasicBeefTest {
    private static final long DIFFERENCE_TIME = 200L;

    /**
     * Tests, if {@link Beef} adheres to the equals contract. This test uses
     * the external class {@link EqualsTester}.
     */
    @SuppressWarnings({"AnonymousInnerClass", "EmptyClass", "JUnitTestMethodWithNoAssertions"})
    @Test
    public final void shouldConformEqualsContract() {
        final Clock clock = new SystemClock();
        final Date testStamp1 = new Date();
        final Date testStamp2 = new Date(testStamp1.getTime() + DIFFERENCE_TIME);
        final Beef objectA = new Beef(clock, testStamp1);
        final Beef objectB = new Beef(clock, testStamp1);
        final Beef objectC = new Beef(clock, testStamp2);
        final Beef objectD = new Beef(clock, testStamp1) {};
        //noinspection ResultOfObjectAllocationIgnored
        new EqualsTester(objectA, objectB, objectC, objectD);
    }

    /**
     * Simple test if toString does provide the correct format
     */
    @Test
    public final void shouldHaveCorrectFormatWhenToStringIsCalled() {
        final Clock clock = new SystemClock();
        final Date now = new Date();
        final Beef beef = new Beef(clock, now);

        //noinspection HardCodedStringLiteral,NestedMethodCall,DuplicateStringLiteralInspection
        Assert.assertThat(beef.toString(), Is.is(String.format("Beef{clock=SystemClock, expiryDate=%s}", now.toString())));
    }
}
