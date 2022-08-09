package com.chriswarrick.lockscreendarkifier;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonDark).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WallpaperHandler.setDark(MainActivity.this.getApplicationContext());
            }
        });

        findViewById(R.id.buttonLight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WallpaperHandler.setLight(MainActivity.this.getApplicationContext());
            }
        });
        findViewById(R.id.buttonNow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DarkManager.setFromCurrentTime(MainActivity.this.getApplicationContext());
            }
        });
        findViewById(R.id.buttonSetAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DarkManager.createNextAlarm(MainActivity.this.getApplicationContext());
            }
        });
    }
}
