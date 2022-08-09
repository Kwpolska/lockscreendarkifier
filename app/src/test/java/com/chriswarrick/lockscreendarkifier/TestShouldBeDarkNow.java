package com.chriswarrick.lockscreendarkifier;

import java.util.Calendar;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestShouldBeDarkNow {
    private static final Calendar startTime = new Calendar.Builder().setDate(2022, 1, 1).setTimeOfDay(0, 0, 0).build();

    @Test
    public void testDarkAtNightBeforeTime() {
        assertTrue(shouldBeDarkNow(0, 0));
        assertTrue(shouldBeDarkNow(0, 50));
        assertTrue(shouldBeDarkNow(5, 0));
        assertTrue(shouldBeDarkNow(5, 59));
        assertTrue(shouldBeDarkNow(6, 0));
        assertTrue(shouldBeDarkNow(6, 25));
    }

    @Test
    public void testLightShortlyAfterTime() {
        assertFalse(shouldBeDarkNow(6, 30));
        assertFalse(shouldBeDarkNow(6, 35));
        assertFalse(shouldBeDarkNow(7, 0));
        assertFalse(shouldBeDarkNow(8, 0));
    }

    @Test
    public void testLightMiddleOfDay() {
        assertFalse(shouldBeDarkNow(12, 30));
        assertFalse(shouldBeDarkNow(12, 35));
        assertFalse(shouldBeDarkNow(17, 0));
        assertFalse(shouldBeDarkNow(18, 0));
    }

    @Test
    public void testLightInTheEvening() {
        assertFalse(shouldBeDarkNow(21, 59));
        assertFalse(shouldBeDarkNow(22, 0));
        assertFalse(shouldBeDarkNow(22, 25));
    }

    @Test
    public void testDarkEndOfDay() {
        assertTrue(shouldBeDarkNow(22, 35));
        assertTrue(shouldBeDarkNow(23, 0));
        assertTrue(shouldBeDarkNow(23, 59));
    }

    private static boolean shouldBeDarkNow(int hour, int minute) {
        Calendar time = (Calendar)startTime.clone();
        time.set(Calendar.HOUR_OF_DAY, hour);
        time.set(Calendar.MINUTE, minute);
        time.set(Calendar.SECOND, 0);
        return DarkManager.shouldBeDarkNow(time);
    }
}
