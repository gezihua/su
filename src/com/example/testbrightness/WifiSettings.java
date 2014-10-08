
package com.example.testbrightness;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.provider.Settings;

public class WifiSettings extends SettingsManager{
    private static WifiManager mWifiManager;
    public WifiSettings(){
        super();
        mWifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);;
    }
    private static void getInstance(){
        if(mWifiManager == null){
            new WifiSettings();
        }
    }
    /*turn the state of wifi opposite
     * */
    public static void setWifiChaged() {
        getInstance();
        if (mWifiManager.isWifiEnabled()) {
            mWifiManager.setWifiEnabled(false);
        } else {
            mWifiManager.setWifiEnabled(true);
        }
    }
    static void setWifiUnEnabled(boolean flag){
        mWifiManager.setWifiEnabled(flag);
    }
    /*show that wifi is available or not
     * */
    public static boolean isEnabled(){
        getInstance();
        return mWifiManager.isWifiEnabled();
    }
    /**
     * added for caller of WifiActivity 
     */
    public static void showSettings() {
        getInstance();
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }
}
