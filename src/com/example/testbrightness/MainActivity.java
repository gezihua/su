
package com.example.testbrightness;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.os.Build;
import android.provider.Settings;

public class MainActivity extends Activity implements IBrightnessSettings {
    private final int SCREEN_BRIGHT_MODE_AUTO = 1;
    private final int SCREEN_BRINGT_MODE_MANAL = 0;
    PlaceholderFragment mPlaceholderFragment;
    static boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlaceholderFragment = new PlaceholderFragment();
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, mPlaceholderFragment)
                    .commit();
        }
        registerSystemModeChangedReceiver();
        SystemModeChanged.bindRegiregisterObserver(this);
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Log.e("print", "start");
        this.sendBroadcast(new Intent(SettingsConstants.MODE_CHANGED));
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
    }

    private void registerSystemModeChangedReceiver() {
        IntentFilter localIntentFilter1 = SystemModeChanged.getRegiregisterReceiverIntentFilter();
        SystemModeChanged mSystemModeChanged = new SystemModeChanged();
        mSystemModeChanged.bindISyncIcno(mPlaceholderFragment);
        this.registerReceiver(mSystemModeChanged, localIntentFilter1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public class PlaceholderFragment extends Fragment implements ISyncIcon {
        WindowManager.LayoutParams layoutParams;

        View rootView;
        View scroll;
        ImageView sound;
        ImageView wifi;
        ImageView gps;
        ImageView gprs;
        ImageView blue;
        ImageView air;
        ImageView bright;
        ImageView rotate;
        SeekBar seekBar;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // LayoutInflater inflater = (LayoutInflater)
            // getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rootView = inflater.inflate(R.layout.fragment_main, container, false);
            // scroll = rootView.findViewById(R.id.mian_scroll);
            initView();
            return rootView;
        }

        private void initView() {
            ScrollView view;
            HorizontalScrollView view1;
            Log.e("print", "initView");
            sound = (ImageView) rootView.findViewById(R.id.sound);
            sound.setOnClickListener(new SoundClickListener());
            wifi = (ImageView) rootView.findViewById(R.id.wifi);
            wifi.setOnClickListener(new WifiClickListener());
            wifi.setOnLongClickListener(new WifiLongClickListener());
            gps = (ImageView) rootView.findViewById(R.id.gps);
            gps.setOnClickListener(new GpsClickListener());
            gprs = (ImageView) rootView.findViewById(R.id.gprs);
            gprs.setOnClickListener(new GprsClickListener());
            blue = (ImageView) rootView.findViewById(R.id.blue);
            blue.setOnClickListener(new BlueToothClickListener());
            bright = (ImageView) rootView.findViewById(R.id.bright);
            bright.setOnClickListener(new BrightOnClickListener());
            seekBar = (SeekBar) rootView.findViewById(R.id.seekBar);
            seekBar.setOnSeekBarChangeListener(new SeekbarChangeListener());
            // View lock = (View) rootView.findViewById(R.id.light);
            // lock.setOnClickListener(new TorchClickListener());
            air = (ImageView) rootView.findViewById(R.id.air);
            air.setOnClickListener(new AirOnClickListener());
            rotate = (ImageView) rootView.findViewById(R.id.rotate);
            rotate.setOnClickListener(new RotateOnClickListener());
            // bindBrightness();
        }

        private final class AirOnClickListener implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                AirplaneModeSettings.showSettings();
            }

        }

        private final class BrightOnClickListener implements View.OnClickListener {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                BrightnessSettings.setSreenBrightMode(SettingsConstants.getBrightnessMode());
                SettingsConstants.setBrightnessMode();
            }
        }

        private final class RotateOnClickListener implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                SettingsConstants.getMode().rotate = (RotateSettings.getRotationStatus() == 1) ? 0
                        : 1;
                RotateSettings.setRotateStatus(SettingsConstants.getMode().rotate);
            }

        }

        private final class BlueToothClickListener implements View.OnClickListener {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                BluetoothSettings.setBluetoothChanged();
            }

        }

        private class WifiClickListener implements View.OnClickListener {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Log.e("print", "wifi");
                WifiSettings.setWifiChaged();
            }

        }

        private class WifiLongClickListener implements View.OnLongClickListener {

            @Override
            public boolean onLongClick(View arg0) {
                // TODO Auto-generated method stub
                WifiSettings.showSettings();
                return true;
            }

        }

        private final class GpsClickListener implements View.OnClickListener {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                GpsSettings.showSettings();
            }

        }

        private final class GprsClickListener implements View.OnClickListener {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                GprsSettings.setConnectivityChanged();
            }

        }

        private final class SoundClickListener implements View.OnClickListener {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SettingsConstants.setSound();
                SoundSettings.setMode(SettingsConstants.getSound());
            }

        }

        private class SeekbarChangeListener implements SeekBar.OnSeekBarChangeListener {
            int drag = 0;

            @Override
            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                // TODO Auto-generated method stub
                if (arg2)
                    drag = arg1;
                bindBrightness();
                BrightnessSettings.setScreenBrightnessWithoutMode(drag, 1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub
                flag = false;
                BrightnessSettings.setScreenBrightnessWithoutMode(drag, 2);

            }
        }

        private final class LockLongClickListener implements View.OnLongClickListener {

            @Override
            public boolean onLongClick(View arg0) {
                // TODO Auto-generated method stub
                LockScreenSettings.changed();
                return true;
            }

        }

        @Override
        public void changeIcno(String action) {
            // TODO Auto-generated method stub
            boolean in = action.equalsIgnoreCase(SystemModeChanged.MODE_CHANGED);
            if (action.equalsIgnoreCase(SystemModeChanged.RINGER_MODE_CHANGED) || in) {
                Log.e("print", "RINGER_MODE_CHANGED");
                int mode = SoundSettings.getMode();
                changeSoundIcno(mode);
            }
            if (action.equalsIgnoreCase(SystemModeChanged.WIFI_STATE_CHANGED) || in) {
                Log.e("print", "WIFI_STATUS_CHANGED");
                boolean flag = WifiSettings.isEnabled();
                changeWifiIcon(flag);
            }
            if (action.equalsIgnoreCase(SystemModeChanged.GPRS_STATUS_CHANGED) || in
                    || action.equalsIgnoreCase(SystemModeChanged.SERVICE_STATE)) {

                // boolean flag = GprsSettings.isNetworkAvailable(intent);
                // Log.e("print", "GPRS_STATUS_CHANGED" + flag);
                boolean flag = GprsSettings.getGprsEnabled();
                changeGprsIcon(flag);
            }
            if (action.equalsIgnoreCase(SystemModeChanged.BLUE_STATUS_CHANGED) || in) {
                Log.e("print", "BLUE_STATUS_CHANGED");
                boolean flag = BluetoothSettings.getBluetoothEnabled();
                changeBlueToothIcon(flag);
            }
            if (action.equalsIgnoreCase(SystemModeChanged.GPS_STATUS_CHANGED) || in) {
                Log.e("print", "GPS_STATUS_CHANGED");
                boolean flag = GpsSettings.isEnabled();
                changeGpsIcon(flag);
            }
            if (action.equalsIgnoreCase(SystemModeChanged.AIR_PLANE_MODE_ON) || in) {
                Log.e("print", "AIR_PLANE_MODE_CHANGED=========");
                boolean flag = (AirplaneModeSettings.getAirplaneMode() == 1);
                changeAirplaneIcon(flag);
                // if(!flag)
                // GprsSettings.setEnabled(false);
                // boolean flag1= (GprsSettings.isNetworkAvailable());
                // changeGprsIcon(flag1);
            }
            if (action.equalsIgnoreCase(SystemModeChanged.ROTATE_MODE_CHANGED) || in) {
                Log.e("print", "ROTATE_MODE_CHANGED=========");
                boolean flag = (RotateSettings.getRotationStatus() == 1);
                changeRotateIcon(flag);
            }
            if ((action.equalsIgnoreCase(SystemModeChanged.BRIGHTNESS_MODE_CHANGED)
                    || action.equalsIgnoreCase(SystemModeChanged.BRIGHTNESS_CHANGED) || in)) {
                boolean flag = (BrightnessSettings.getSreenBrightMode() == 1);
                Log.e("print", "BRIGHTNESS_MODE_CHANGED" + flag);
                changeBrightnessModeIcon(flag);
            }
            if ((action.equalsIgnoreCase(SystemModeChanged.BRIGHTNESS_CHANGED) || in)) {
                int temp = BrightnessSettings.getScreentBrightness();
                setWindowsLayout(temp);
                if (!flag) {
                    flag = true;
                    return;
                }
                temp = (temp <= 10) ? 0 : temp;
                seekBar.setProgress(temp);
            }

        }

        private void changeSoundIcno(int mode) {
            switch (mode) {
                case 0: {
                    sound.setImageResource(R.drawable.icon_volume_d);
                    break;
                }
                case 1: {
                    sound.setImageResource(R.drawable.icon_vibrate);
                    break;
                }
                case 2: {
                    sound.setImageResource(R.drawable.icon_volume);
                    break;
                }
            }

        }

        private void changeWifiIcon(boolean flag) {
            if (flag) {
                wifi.setImageResource(R.drawable.icon_wifi);
            } else {
                wifi.setImageResource(R.drawable.icon_wifi_d);
            }
        }

        private void changeBlueToothIcon(boolean flag) {
            if (flag) {
                blue.setImageResource(R.drawable.icon_bluetooth);
            } else {
                blue.setImageResource(R.drawable.icon_bluetooth_d);
            }
        }

        private void changeGpsIcon(boolean flag) {
            if (flag) {
                gps.setImageResource(R.drawable.icon_gps);
            } else {
                gps.setImageResource(R.drawable.icon_gps_d);
            }
        }

        private void changeGprsIcon(boolean flag) {
            if (flag) {
                gprs.setImageResource(R.drawable.icon_data);
            } else {
                gprs.setImageResource(R.drawable.icon_data_d);
            }
        }

        private void changeAirplaneIcon(boolean flag) {
            if (flag) {
                air.setImageResource(R.drawable.icon_flight);
            } else {
                air.setImageResource(R.drawable.icon_flight_d);
            }
        }

        private void changeRotateIcon(boolean flag) {
            if (flag) {
                rotate.setImageResource(R.drawable.icon_revolve);
            } else {
                rotate.setImageResource(R.drawable.icon_revolve_d);
            }
        }

        private void changeBrightnessModeIcon(boolean flag) {
            if (flag) {
                bright.setImageResource(R.drawable.icon_light_a);
            } else {
                bright.setImageResource(R.drawable.icon_light);
            }
        }

    }

    public final class TorchClickListener implements View.OnClickListener {

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            // Intent intent = new
            // Intent(MainActivity.this,com.example.testbrightness.TorchActivity.class);
            // intent.putExtra("isLight", true);
            // startActivity(intent);
            // SettingsConstants.torch = !SettingsConstants.torch;
            // TorchSettings.setisLight(SettingsConstants.torch);
        }

    }

    public void bindBrightness() {
        BrightnessSettings.bindIBrightnessSettings(this);
    }

    @Override
    public void setWindowsLayout(int screenbrightness) {
        // TODO Auto-generated method stub
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        float brightness = 1.0f / 255 * screenbrightness;
        if (brightness == 0)
            brightness = 0.01f;
        lp.screenBrightness = brightness;
        getWindow().setAttributes(lp);
    }

    private Activity getContext() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }
}
