
package com.example.testbrightness;

import android.content.ContentResolver;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;

public class AirplaneModeSettings extends SettingsManager {
    private static ContentResolver mContentResolver;
    private static int AIRPLANE_MODE = 0;

    private AirplaneModeSettings() {
        super();
        mContentResolver = mContext.getContentResolver();
    }

    private static void getInstance() {
        if (mContext == null || mContentResolver == null) {
            new AirplaneModeSettings();
        }
    }

    public static void showSettings() {
        getInstance();
        Intent intent = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    /**
     * @return AirplaneMode is on or not
     */
    public static int getAirplaneMode() {
        getInstance();
        try {
            if(mContentResolver == null){
                Log.e("print", "null"); 
            }
            return Settings.System.getInt(mContentResolver, Settings.System.AIRPLANE_MODE_ON,AIRPLANE_MODE);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("print", "error");
            return 0;
        }
    }
}
