package com.chriswarrick.lockscreendarkifier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Objects;

public class BootBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Objects.equals(intent.getAction(), Intent.ACTION_BOOT_COMPLETED)) {
            Log.i("LSD/BootBroadcastReceiver", "Received boot broadcast");
            DarkManager.setFromCurrentTime(context);
            DarkManager.createNextAlarm(context);
        }
    }
}
