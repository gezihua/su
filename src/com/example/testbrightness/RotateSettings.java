
package com.example.testbrightness;

import android.content.ContentResolver;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.util.Log;

public class RotateSettings extends SettingsManager {
    private static ContentResolver mContentResolver = null;

    private RotateSettings() {
        super();
        mContentResolver = mContext.getContentResolver();
    }

    private static void getIntance() {
        if (mContext == null || mContentResolver == null) {
            new RotateSettings();
        }
    }

    /**
     * if 0 the window will not rotate ,otherwise 1 will rotate ,and -1 is
     * failed
     * 
     * @return
     */
    public static int getRotationStatus() {
        try {
            getIntance();
            return android.provider.Settings.System.getInt(mContentResolver,
                    Settings.System.ACCELEROMETER_ROTATION);
        } catch (SettingNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("print", "error");
            return -1;
        }
    }

    public static void setRotateStatus(int status) {
        if (status != 1 && status != 0) {
            return;
        }
        try {
            getIntance();
            android.provider.Settings.System.putInt(mContentResolver,
                    Settings.System.ACCELEROMETER_ROTATION, status);
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("print", "error");
        }
    }

}
