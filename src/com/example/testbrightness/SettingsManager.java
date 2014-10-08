
package com.example.testbrightness;

import android.content.Context;

public class SettingsManager {
    public static Context mContext;

    protected SettingsManager() {
        mContext = KuaiApplication.mApplication.getApplicationContext();
    }
}
