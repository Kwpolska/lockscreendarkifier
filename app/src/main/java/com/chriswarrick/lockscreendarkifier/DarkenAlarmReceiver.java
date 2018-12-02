package com.chriswarrick.lockscreendarkifier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DarkenAlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("LSD/DarkenAlarmReceiver", "Going dark from alarm");
        WallpaperHandler.setDark(context);
        DarkManager.createNextAlarm(context, true);
    }
}
