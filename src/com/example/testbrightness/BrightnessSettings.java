
package com.example.testbrightness;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.util.Log;

public class BrightnessSettings extends SettingsManager {
    private final static int SCREEN_BRIGHT_MODE_AUTO = Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
    private final static int SCREEN_BRINGT_MODE_MANAL = Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL;
    private final static int SCREEN_BRIGHT_MODE_ERROR = -1;
    private static ContentResolver mContentResolver = null;
    private static IBrightnessSettings windowBright = null;

    private BrightnessSettings() {
        super();
        mContentResolver = mContext.getContentResolver();
    }

    public static void bindIBrightnessSettings(IBrightnessSettings windowBright) {
        BrightnessSettings.windowBright = windowBright;
    }

    private static void getInstance() {
        if (mContentResolver == null || mContentResolver == null) {
            new BrightnessSettings();
        }
    }

    /**
     * if 1 the mode is atuomatic ,if 0 is manual
     * 
     * @return
     */
    public static int getSreenBrightMode() {
        try {
            getInstance();
            Log.e("print", "getSreenBrightMode1");
            return Settings.System.getInt(mContentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return SCREEN_BRIGHT_MODE_ERROR;
        }
    }

    /**
     * @param mode
     * @return the settings is failed and
     */
    public static void setSreenBrightMode(int mode) {
        if (mode != SCREEN_BRIGHT_MODE_AUTO && mode != SCREEN_BRINGT_MODE_MANAL) {
            return;
        }
        getInstance();
        SettingsConstants.getMode().brightnessMode = mode;
        android.provider.Settings.System.putInt(mContentResolver,
                Settings.System.SCREEN_BRIGHTNESS_MODE, mode);
    }

    public static int getScreentBrightness() {
        if (getSreenBrightMode() == SCREEN_BRIGHT_MODE_AUTO) {
            return -1;
        }
        try {
            getInstance();
            return Settings.System.getInt(mContentResolver, Settings.System.SCREEN_BRIGHTNESS);
        } catch (SettingNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
    }

    private static void setSreenBrightness(int brightness) {
        if (brightness > 255 || brightness < 0) {
            return;
        }
        getInstance();
        saveInstance( Settings.System.SCREEN_BRIGHTNESS, brightness);
    }

    public static void setScreenBrightnessFine() {
        setScreenBrightnessWithoutMode(122, 3);
    }

    /**
     * @param brightness
     * @param flag if 1 will set screenbright ,if 2 will set screenSettings ,if
     *            3 will set both
     */
    public static void setScreenBrightnessWithoutMode(int brightness, int flag) {
        if (flag < 1 || flag > 2) {
            return;
        }
        if (flag % 2 == 1) {
            getInstance();
            saveInstance( Settings.System.SCREEN_BRIGHTNESS_MODE, SCREEN_BRINGT_MODE_MANAL);
            windowBright.setWindowsLayout(brightness);
        }
        if (flag % 2 == 0) {
            setSreenBrightness(brightness);
        }
    }

    private static void saveInstance(final String tag, final int val) {
        final Uri uri = android.provider.Settings.System.getUriFor(tag);
        android.provider.Settings.System.putInt(mContext.getContentResolver(), tag, val);
        mContentResolver.notifyChange(uri, null);
    }
}
