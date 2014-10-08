
package com.example.testbrightness;

import android.content.*;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.util.Log;

public class GprsSettings extends SettingsManager {
    private static ConnectivityManager mConnectivityManager;

    private GprsSettings() {
        super();
        if (mConnectivityManager == null) {
            mConnectivityManager = (ConnectivityManager) mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
        }
    }

    private static void getInstance() {
        if (mConnectivityManager == null) {
            // mContext = Activity.
            new GprsSettings();
        }
    }

    public static void setConnectivityChanged() {
        getInstance();
        if (mConnectivityManager.getMobileDataEnabled()) {
            mConnectivityManager.setMobileDataEnabled(false);
        } else {
            mConnectivityManager.setMobileDataEnabled(true);
        }
    }

    static void setEnabled(boolean flag) {
        mConnectivityManager.setMobileDataEnabled(flag);
    }

    public static boolean getGprsEnabled() {
        getInstance();
        return mConnectivityManager.getMobileDataEnabled();
    }

    public static boolean isNetworkAvailable() {
        getInstance();
        // Log.e("print", "intent"+intent+intent.getExtras());
        NetworkInfo networkInfo = mConnectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        State state = networkInfo.getState();
        return (state == State.CONNECTED || state == State.CONNECTING);
    }

}
