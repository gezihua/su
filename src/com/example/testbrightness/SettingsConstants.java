
package com.example.testbrightness;

/**
 * operate System mode ,and mode contains wifi gprs gps roate airplane bluetooth
 * brightness brightnessmode and ringermode
 */
public final class SettingsConstants {

    /**
     * the broadcast will send the intent broadcast action
     */
    public final static String MODE_CHANGED = "com.kuaiLauncher.ACTION.REFRESH_ICON";
    /**
     * mode contains wifi gprs gps roate airplane bluetooth brightness
     * brightnessmode and ringermode
     */
    private static Mode mode = null;

    // /**
    // * if wifi is false the wifi service is not available
    // */
    // public static boolean wifi = false;
    //
    // /**
    // * if the bluetooth is true ,the device of bluetooth is available,but the
    // * remote devices will not be available
    // */
    // public static boolean bluetooth = false;
    //
    // /**
    // * if sound is 0 the ringermode is slience if sound is 1 the ringermode is
    // * viberation if sound is 2 the ringermode is normal
    // */
    // static int sound = 0;
    // /**
    // * if 0 the brightness mode is manual ,otherwise ,if 1 the mode is
    // automatic
    // */
    // static int brightnessMode = 0;
    // /**
    // * if brightness mode is manual ,the brightness is available
    // */
    // static int brightness = 0;
    // /**
    // * if gprs is true the network connection is available ,otherwise the
    // * network connection is not
    // */
    // public static boolean gprs = false;
    //
    // /**
    // * if gps is true ,the location service is available
    // */
    // public static boolean gps = false;
    //
    // /**
    // * add for torch mode
    // */
    // public static boolean torch = false;
    // /**
    // * if airplanemode is 1 ,the airplanemode is used, otherwise the
    // * airplanemode is not used
    // */
    // static int airplaneMode = 0;
    // /**
    // * the rotate is used if 1
    // */
    // public static int rotate = 0;

    public static Mode getMode() {
        getInstance();
        return mode;
    }

    public static void setMode(Mode mode) {
        SettingsConstants.mode = mode;
    }

    private SettingsConstants() {
        mode = new Mode();
    }

    public SettingsConstants(Mode mode) {
        SettingsConstants.mode = mode;
    }

    public SettingsConstants(boolean wifi, boolean gprs, boolean gps, boolean bluetooth,
            int brightnessmode, int brightness, int sound, int rotate) {
        getInstance();
        SettingsConstants.mode.wifi = wifi;
        SettingsConstants.mode.gprs = gprs;
        SettingsConstants.mode.gps = gps;
        SettingsConstants.mode.bluetooth = bluetooth;
        SettingsConstants.mode.brightnessMode = brightnessmode;
        SettingsConstants.mode.brightness = brightness;
        SettingsConstants.mode.sound = sound;
        SettingsConstants.mode.rotate = rotate;
    }

    private static void getInstance() {
        if (mode == null) {
            new SettingsConstants();
        }
    }

    public static int getBrightnessMode() {
        getInstance();
        return mode.brightnessMode;
    }

    public static void setBrightnessMode() {
        getInstance();
        mode.brightnessMode += 1;
        if (mode.brightnessMode > 1) {
            mode.brightnessMode = 0;
        }
    }

    public static int getSound() {
        getInstance();
        return (mode.sound < 0 || mode.sound > 2) ? 0 : mode.sound;
    }

    public static void setSound() {
        // TODO change the basic mode slience ,ringer or vibreation
        getInstance();
        mode.sound += 1;
        if (mode.sound < 0 || mode.sound > 2) {
            mode.sound = 0;
        }
    }

    public static void changeMode(Mode mode) {
        WifiSettings.setWifiUnEnabled(mode.wifi);
        BluetoothSettings.setEnabled(mode.bluetooth);
        BrightnessSettings.setSreenBrightMode(mode.brightnessMode);
        if (mode.brightnessMode == 0) {
            BrightnessSettings.setScreenBrightnessWithoutMode(mode.brightness, 3);
        }
        SoundSettings.setMode(mode.sound);
        GprsSettings.setEnabled(mode.gprs);
        RotateSettings.setRotateStatus(mode.rotate);
    }

    public static String[] getStringSpilt(String content) {
        try {
            return ((content.endsWith("") || content == null || !content.contains("#")) ? null
                    : content.split("#"));
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public class Mode {
        /**
         * if wifi is false the wifi service is not available
         */
        public boolean wifi = false;

        /**
         * if the bluetooth is true ,the device of bluetooth is available,but
         * the remote devices will not be available
         */
        public boolean bluetooth = false;

        /**
         * if sound is 0 the ringermode is slience if sound is 1 the ringermode
         * is viberation if sound is 2 the ringermode is normal
         */
        public int sound = 0;
        /**
         * if 0 the brightness mode is manual ,otherwise ,if 1 the mode is
         * automatic
         */
        public int brightnessMode = 0;
        /**
         * if brightness mode is manual ,the brightness is available
         */
        public int brightness = 0;
        /**
         * if gprs is true the network connection is available ,otherwise the
         * network connection is not
         */
        public boolean gprs = false;

        /**
         * if gps is true ,the location service is available
         */
        public boolean gps = false;
        /**
         * if airplanemode is 1 ,the airplanemode is used, otherwise the
         * airplanemode is not used
         */
        public int airplaneMode = 0;
        /**
         * the rotate is used if 1
         */
        public int rotate = 0;
    }

}
