
package com.example.testbrightness;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.provider.Settings;

public class BluetoothSettings extends SettingsManager {
    private static final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    /**
     * @return the action is successful or not
     */
    public BluetoothSettings() {
        super();
    }

    public static void setBluetoothChanged() {
        if (mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();
        } else {
            mBluetoothAdapter.enable();
        }
    }
    static void setEnabled(boolean flag){
        if(flag){
            mBluetoothAdapter.disable();
        } else {
            mBluetoothAdapter.enable();
        }
    }
    public static boolean getBluetoothEnabled() {
        return mBluetoothAdapter.isEnabled();
    }

    public static void getBluetoothSettings() {
        mContext.startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
    }

    public static boolean handleAdapterStateChange(int adapterState) {
        return (adapterState == BluetoothAdapter.STATE_ON);
    }
}
