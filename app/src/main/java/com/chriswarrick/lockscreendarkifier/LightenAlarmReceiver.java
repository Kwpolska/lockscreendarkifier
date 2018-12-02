package com.chriswarrick.lockscreendarkifier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class LightenAlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("LSD/LightenAlarmReceiver", "Going light from alarm");
        WallpaperHandler.setLight(context);
        DarkManager.createNextAlarm(context, true);
    }
}
