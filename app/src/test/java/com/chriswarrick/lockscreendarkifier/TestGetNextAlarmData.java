package com.chriswarrick.lockscreendarkifier;

import java.util.Calendar;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestGetNextAlarmData {
    @Test
    public void testDarkAtNightBeforeTime() {
        assertEquals(thisDayEnd, getNextAlarmData(0, 0));
        assertEquals(thisDayEnd, getNextAlarmData(0, 50));
        assertEquals(thisDayEnd, getNextAlarmData(5, 0));
        assertEquals(thisDayEnd, getNextAlarmData(5, 59));
        assertEquals(thisDayEnd, getNextAlarmData(6, 0));
        assertEquals(thisDayEnd, getNextAlarmData(6, 25));
    }

    @Test
    public void testLightShortlyAfterTime() {
        assertEquals(withSetNow(start, StateRequest.GOES_LIGHT), getNextAlarmData(6, 30));
        assertEquals(start, getNextAlarmData(6, 35));
        assertEquals(start, getNextAlarmData(7, 0));
        assertEquals(start, getNextAlarmData(8, 0));
    }

    @Test
    public void testLightMiddleOfDay() {
        assertEquals(start, getNextAlarmData(12, 30));
        assertEquals(start, getNextAlarmData(12, 35));
        assertEquals(start, getNextAlarmData(17, 0));
        assertEquals(start, getNextAlarmData(18, 0));
    }

    @Test
    public void testLightInTheEvening() {
        assertEquals(start, getNextAlarmData(21, 59));
        assertEquals(start, getNextAlarmData(22, 0));
        assertEquals(start, getNextAlarmData(22, 25));
    }

    @Test
    public void testDarkEndOfDay() {
        assertEquals(withSetNow(nextDayEnd, StateRequest.GOES_DARK), getNextAlarmData(22, 30));
        assertEquals(nextDayEnd, getNextAlarmData(22, 35));
        assertEquals(nextDayEnd, getNextAlarmData(23, 0));
        assertEquals(nextDayEnd, getNextAlarmData(23, 59));
    }


    private static final Calendar midnight = new Calendar.Builder().setDate(2022, 1, 1).setTimeOfDay(0, 0, 0).build();
    private static final Calendar thisDayEndTime = new Calendar.Builder().setDate(2022, 1, 1).setTimeOfDay(6, 30, 0).build();
    private static final Calendar startTime = new Calendar.Builder().setDate(2022, 1, 1).setTimeOfDay(22, 30, 0).build();
    private static final Calendar nextDayEndTime = new Calendar.Builder().setDate(2022, 1, 2).setTimeOfDay(6, 30, 0).build();

    private static final NextAlarmData thisDayEnd = new NextAlarmData(StateRequest.GOES_LIGHT, thisDayEndTime.getTimeInMillis());
    private static final NextAlarmData start = new NextAlarmData(StateRequest.GOES_DARK, startTime.getTimeInMillis());
    private static final NextAlarmData nextDayEnd = new NextAlarmData(StateRequest.GOES_LIGHT, nextDayEndTime.getTimeInMillis());

    private static NextAlarmData withSetNow(NextAlarmData nextAlarmData, StateRequest setNow) {
        NextAlarmData result = (NextAlarmData)nextAlarmData.clone();
        result.setNow = setNow;
        return result;
    }

    private static NextAlarmData getNextAlarmData(int hour, int minute) {
        Calendar time = (Calendar)midnight.clone();
        time.set(Calendar.HOUR_OF_DAY, hour);
        time.set(Calendar.MINUTE, minute);
        return DarkManager.getNextAlarmData(time);
    }
}
