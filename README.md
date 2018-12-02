Lock Screen Darkifier
=====================

Darkify your Android phone’s lock screen at night. May or may not prevent burned eyes due to bright wallpapers.

How to use
----------

Since I’m too lazy to do this app *right*, there is no easy prebuilt package and no settings screen. And there probably will never be one.

To get this to work:

1. Put your preferred hours in `app/src/main/java/com/chriswarrick/lockscreendarkifier/Options.java` and hope that assumptions made in the code fit your schedule.
2. Put a dark wallpaper of your choosing in `app/src/main/res/raw/dark_wallpaper.png`.
3. Build and run on your device(s).

After install, you need to open the app and click the “Set up timer” button. (No need to do this after rebooting.)

(PS. yes, I know, it’s *darkener*. Darkifier sounds cooler though.)
