package com.coelle_online;

import com.gargoylesoftware.base.testing.EqualsTester;
import org.hamcrest.core.Is;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

/** Description User: coellen Date: 28.07.12 Time: 17:05 */
public class BasicBeefTest {
    @SuppressWarnings({"AnonymousInnerClass", "EmptyClass", "JUnitTestMethodWithNoAssertions"})
    @Test
    public final void shouldConformEqualsContract() {
        final DateTime testStamp1 = new DateTime();
        final DateTime testStamp2 = new DateTime();
        final Beef objectA = new Beef(testStamp1);
        final Beef objectB = new Beef(testStamp1);
        final Beef objectC = new Beef(testStamp2);
        final Beef objectD = new Beef(testStamp1) {};
        //noinspection ResultOfObjectAllocationIgnored
        new EqualsTester(objectA, objectB, objectC, objectD);
    }

    @Test
    public final void shouldHaveCorrectFormatWhenToStringIsCalled() {
        final DateTime now = new DateTime();
        final Beef beef = new Beef(now);

        //noinspection HardCodedStringLiteral,NestedMethodCall,DuplicateStringLiteralInspection
        Assert.assertThat(beef.toString(), Is.is(String.format("Beef{expiryDate=%s}", now.toString())));
    }
}
