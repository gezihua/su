
package com.example.testbrightness;

import android.content.ContentResolver;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;

public class GpsSettings extends SettingsManager {
    private static ContentResolver mContentResolver;

    private GpsSettings() {
        super();
        mContentResolver = mContext.getContentResolver();
    }
 /* 
  * get the object instance
  * */
    private static void getInstance() {
        if (mContentResolver == null) {
            new GpsSettings();
        }

    }

    public static boolean isEnabled() {
        getInstance();
        return Settings.Secure.isLocationProviderEnabled(mContentResolver,
                LocationManager.GPS_PROVIDER);
    }

    /*
     * added for some phones
     */
    private static void changed() {
        getInstance();
        boolean flag = isEnabled();
        Settings.Secure.setLocationProviderEnabled(mContentResolver, LocationManager.GPS_PROVIDER,
                !flag);
    }

    public static void showSettings() {
        getInstance();
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

}
