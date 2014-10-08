
package com.example.testbrightness;

import android.content.Context;
import android.media.AudioManager;

public class SoundSettings extends SettingsManager {
    private static AudioManager mAudioManager;
    private static int Mode = 0;
    public static int RINGER_MODE_NORMAL = AudioManager.RINGER_MODE_NORMAL;
    public static int RINGER_MODE_SILENT = AudioManager.RINGER_MODE_SILENT;
    public static int RINGER_MODE_VIBRATE = AudioManager.RINGER_MODE_VIBRATE;

    private SoundSettings() {
        super();
        mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
    }

    private static void getIntance() {
        if (mAudioManager == null) {
            new SoundSettings();
        }
    }
    private static void changed() {
        getIntance();
        int temp = ((Mode == AudioManager.RINGER_MODE_VIBRATE) ? AudioManager.RINGER_MODE_VIBRATE
                : (Mode == AudioManager.RINGER_MODE_NORMAL ? AudioManager.RINGER_MODE_NORMAL
                        : AudioManager.RINGER_MODE_SILENT));
        mAudioManager.setRingerMode(temp);
    }

    public static void setMode(int mode) {
        Mode = mode;
        SettingsConstants.getMode().sound = mode;
        changed();
    }

    public static int getMode() {
        getIntance();
        return mAudioManager.getRingerMode();
    }

}
