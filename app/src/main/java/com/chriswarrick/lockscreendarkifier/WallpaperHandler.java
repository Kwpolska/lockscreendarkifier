package com.chriswarrick.lockscreendarkifier;

import android.app.WallpaperManager;
import android.content.Context;
import android.util.Log;

import java.io.IOException;

public class WallpaperHandler {
    public static void setLight(Context context) {
        WallpaperManager wm = WallpaperManager.getInstance(context);
        Log.i("LSD/WallpaperHandler", "Going light");
        try {
            wm.clear(WallpaperManager.FLAG_LOCK);
        } catch (IOException e) {
            Log.e("LSD/unsetDark", "Failed to unset dark wallpaper", e);
        }
    }

    public static void setDark(Context context) {
        WallpaperManager wm = WallpaperManager.getInstance(context);
        Log.i("LSD/WallpaperHandler", "Going dark");
        try {
            wm.setResource(R.raw.dark_wallpaper, WallpaperManager.FLAG_LOCK);
        } catch (IOException e) {
            Log.e("LSD/setDark", "Failed to set dark wallpaper", e);
        }
    }
}
