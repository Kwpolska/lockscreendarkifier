package com.chriswarrick.lockscreendarkifier;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.ParcelFileDescriptor;
import androidx.core.app.ActivityCompat;
import android.util.Log;

import java.io.IOException;

public class WallpaperHandler {
    public static void setLight(Context context) {
        WallpaperManager wm = WallpaperManager.getInstance(context);
        Log.i("LSD/WallpaperHandler", "Going light");
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                Log.e("LSD/unsetDark", "No storage permission, cannot get wallpaper.");
                return;
            }

        try (
            ParcelFileDescriptor wallpaperFile = wm.getWallpaperFile(WallpaperManager.FLAG_SYSTEM);
            ParcelFileDescriptor.AutoCloseInputStream wallpaperStream = new ParcelFileDescriptor.AutoCloseInputStream(wallpaperFile)
        ) {
            wm.setStream(wallpaperStream, null, true, WallpaperManager.FLAG_LOCK);
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
