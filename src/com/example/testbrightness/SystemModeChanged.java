
package com.example.testbrightness;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;

/**
 * 1 :the contentoberver will be registered when yout want to synchonized the
 * icno mBrightnessModeObserver mBrightnessChangedObserver mRotateModeChanged
 * mAirplaneModeChanged 2: will bindISyncIcon
 */
public class SystemModeChanged extends BroadcastReceiver {
    static final String SERVICE_STATE = "android.intent.action.SERVICE_STATE";
    static final String RINGER_MODE_CHANGED = "android.media.RINGER_MODE_CHANGED";
    static final String WIFI_STATE_CHANGED = "android.net.wifi.WIFI_STATE_CHANGED";
    static final String GPS_STATUS_CHANGED = "android.location.PROVIDERS_CHANGED";
    static final String INET_CONDITION_ACTION = "android.net.conn.INET_CONDITION_ACTION";
    static final String AIR_PLANE_MODE_CHANGED = "android.intent.action.AIRPLANE_MODE";
    static final String GPRS_STATUS_CHANGED = "android.net.conn.CONNECTIVITY_CHANGE";
    static final String BLUE_STATUS_CHANGED = "android.bluetooth.adapter.action.STATE_CHANGED";
    static final String AIR_PLANE_MODE_ON = "com.kuaiLauncher.action.ACTION_AIRPLANE_MODE_ON";
    static final String ROTATE_MODE_CHANGED = "com.kuaiLauncher.action.ROTATE_MODE_CHANGED";
    static final String BRIGHTNESS_MODE_CHANGED = "com.kuaiLauncher.action.BRIGHTNESS_MODE_CHANGED";
    static final String BRIGHTNESS_CHANGED = "com.kuaiLauncher.action.BRIGHTNESS_CHANGED";
    final static String MODE_CHANGED = "com.kuaiLauncher.ACTION.REFRESH_ICON";
    private ISyncIcon mISyncIcno;

    public SystemModeChanged() {
        super();
    }

    public SystemModeChanged(ISyncIcon mISyncIcno) {
        super();
        bindISyncIcno(mISyncIcno);
    }

    public void bindISyncIcno(ISyncIcon mISyncIcno) {
        this.mISyncIcno = mISyncIcno;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        
        String action = intent.getAction();
        Log.e("print", action);
        // TODO Synchonized the inco
        mISyncIcno.changeIcno(action);
    }

    public final static IntentFilter getRegiregisterReceiverIntentFilter() {
        IntentFilter localIntentFilter1 = new IntentFilter();
        localIntentFilter1.addAction(WIFI_STATE_CHANGED);
        localIntentFilter1.addAction("android.net.wifi.STATE_CHANGE");
        localIntentFilter1.addAction(RINGER_MODE_CHANGED);
        localIntentFilter1.addAction("android.intent.action.SERVICE_STATE");
        localIntentFilter1.addAction("android.bluetooth.BluetoothAdapter.ACTION_STATE_CHANGED");
        localIntentFilter1.addAction(BLUE_STATUS_CHANGED);
        localIntentFilter1.addAction(GPS_STATUS_CHANGED);
        localIntentFilter1.addAction(GPRS_STATUS_CHANGED);
        localIntentFilter1.addAction(AIR_PLANE_MODE_ON);
        localIntentFilter1.addAction(BRIGHTNESS_MODE_CHANGED);
        localIntentFilter1.addAction(AIR_PLANE_MODE_CHANGED);
        localIntentFilter1.addAction(ROTATE_MODE_CHANGED);
        localIntentFilter1.addAction(BRIGHTNESS_CHANGED);
        localIntentFilter1.addAction(MODE_CHANGED);
        localIntentFilter1.addAction(INET_CONDITION_ACTION);
        return localIntentFilter1;
    }

    public static void bindRegiregisterObserver(Context context) {
        context.getContentResolver().registerContentObserver(Settings.System
                .getUriFor(Settings.System.SCREEN_BRIGHTNESS_MODE), false,
                SystemModeChanged.mBrightnessModeObserver);
        context.getContentResolver().registerContentObserver(
                Settings.System.getUriFor(Settings.System.AIRPLANE_MODE_ON), false,
                SystemModeChanged.mAirplaneModeChanged);
        context.getContentResolver().registerContentObserver(
                Settings.System.getUriFor(Settings.System.ACCELEROMETER_ROTATION), false,
                SystemModeChanged.mRotateModeChanged);
        context.getContentResolver().registerContentObserver(
                Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS), false,
                SystemModeChanged.mBrightnessChangedObserver);
    }

    public final static ContentObserver mBrightnessModeObserver = new ContentObserver(new Handler()) {
        @Override
        public void onChange(boolean change) {
            Intent intent = new Intent(BRIGHTNESS_MODE_CHANGED);
            Log.e("print", "BRIGHTNESS_MODE_CHANGED");
            KuaiApplication.mApplication.sendBroadcast(intent);
        }
    };
    public final static ContentObserver mBrightnessChangedObserver = new ContentObserver(
            new Handler()) {

        @Override
        public void onChange(boolean selfChange) {
            // TODO Auto-generated method stub
            Intent intent = new Intent(BRIGHTNESS_CHANGED);
            KuaiApplication.mApplication.sendBroadcast(intent);
        }

    };
    public final static ContentObserver mRotateModeChanged = new ContentObserver(new Handler()) {

        @Override
        public void onChange(boolean selfChange) {
            // TODO Auto-generated method stub
            Intent intent = new Intent(ROTATE_MODE_CHANGED);
            KuaiApplication.mApplication.sendBroadcast(intent);
        }

    };
    // public final static void registeredAllObserver(Context context){
    // context.getContentResolver().registerContentObserver(uri,
    // notifyForDescendents, observer);
    // }
    public final static ContentObserver mAirplaneModeChanged = new ContentObserver(new Handler()) {
        @Override
        public void onChange(boolean change) {
            Intent intent = new Intent(AIR_PLANE_MODE_ON);
            KuaiApplication.mApplication.sendBroadcast(intent);
        }
    };
}
