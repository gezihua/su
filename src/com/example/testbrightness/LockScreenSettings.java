package com.example.testbrightness;

import android.content.Context;
import android.os.PowerManager;
import android.os.SystemClock;

public class LockScreenSettings extends SettingsManager{
    private static  PowerManager mPowerManager; 
    private LockScreenSettings(){
        super();
        mPowerManager = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
    }
    private static void getInstance(){
        if(mPowerManager == null){
            new LockScreenSettings();
        }
    }
    public static void changed(){
        getInstance();
//        mPowerManager.reboot(null);
        mPowerManager.goToSleep(SystemClock.uptimeMillis());
    }
}
