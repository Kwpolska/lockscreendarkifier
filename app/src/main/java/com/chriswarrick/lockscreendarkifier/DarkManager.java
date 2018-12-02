package com.chriswarrick.lockscreendarkifier;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

public class DarkManager {
    static void setFromCurrentTime(Context context) {
        WallpaperHandler.setLight(context);
        if (DarkManager.shouldBeDarkNow()) {
            WallpaperHandler.setDark(context);
        }
    }
    private static boolean shouldBeDarkNow() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        return hour >= Options.STARTS_AT || hour < Options.ENDS_AT;
    }

    static void createNextAlarm(Context context) {
        createNextAlarm(context, false);
    }

    static void createNextAlarm(Context context, boolean ignoreSetNow) {
        NextAlarmData nextAlarmData = getNextAlarmData();
        Intent intent;
        if (nextAlarmData.nextState == StateRequest.GOES_DARK) {
            intent = new Intent(context, DarkenAlarmReceiver.class);
            Log.i("LSD/createNextAlarm", "Setting next alarm to darken");
        } else {
            intent = new Intent(context, LightenAlarmReceiver.class);
            Log.i("LSD/createNextAlarm", "Setting next alarm to lighten");
        }

        if (!ignoreSetNow) {
            switch (nextAlarmData.setNow) {
                case DO_NOTHING:
                    break;
                case GOES_DARK:
                    WallpaperHandler.setDark(context);
                    break;
                case GOES_LIGHT:
                    WallpaperHandler.setLight(context);
                    break;
            }
        }

        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        assert am != null;
        am.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, nextAlarmData.timeInMillis, alarmIntent);
    }

    private static NextAlarmData getNextAlarmData() {
        Calendar time = Calendar.getInstance();
        int hour = time.get(Calendar.HOUR_OF_DAY);
        long nowMillis = time.getTimeInMillis();
        zeroMinSec(time);

        // check if we aren't close to an alarm
        Calendar time2 = (Calendar)time.clone();
        time2.set(Calendar.HOUR_OF_DAY, Options.ENDS_AT);
        if (Math.abs(time2.getTimeInMillis() - nowMillis) <= Options.NEAR_MS) {
            // close to ENDS_AT, so next alarm is at STARTS_AT
            time.set(Calendar.HOUR_OF_DAY, Options.STARTS_AT);
            return new NextAlarmData(StateRequest.GOES_DARK, time.getTimeInMillis(), StateRequest.GOES_LIGHT);
        }

        time2.set(Calendar.HOUR_OF_DAY, Options.STARTS_AT);
        if (Math.abs(time2.getTimeInMillis() - nowMillis) <= Options.NEAR_MS) {
            time.add(Calendar.HOUR_OF_DAY, Options.ADD_WHEN_BEFORE_MIDNIGHT);
            time.set(Calendar.HOUR_OF_DAY, Options.ENDS_AT);
            return new NextAlarmData(StateRequest.GOES_LIGHT, time.getTimeInMillis(), StateRequest.GOES_DARK);
        }


        if (hour >= Options.STARTS_AT && Options.ENDS_AT < Options.STARTS_AT) {
            time.add(Calendar.HOUR_OF_DAY, Options.ADD_WHEN_BEFORE_MIDNIGHT);
            time.set(Calendar.HOUR_OF_DAY, Options.ENDS_AT);
            return new NextAlarmData(StateRequest.GOES_LIGHT, time.getTimeInMillis());
        } else if (hour < Options.ENDS_AT) {
            time.set(Calendar.HOUR_OF_DAY, Options.ENDS_AT);
            return new NextAlarmData(StateRequest.GOES_LIGHT, time.getTimeInMillis());
        } else {
            time.set(Calendar.HOUR_OF_DAY, Options.STARTS_AT);
            return new NextAlarmData(StateRequest.GOES_DARK, time.getTimeInMillis());
        }
    }

    private static void zeroMinSec(Calendar time) {
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);
    }
}
