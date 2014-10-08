
package com.example.testbrightness;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class FilterScreenSettings extends SettingsManager {
    private static LinearLayout mLinearLayout;
    private static WindowManager mWindowManager;
    private static int alpa = 0xa0;
    private static int blue = 0;
    private static int green = 0;
    private static int red = 0;
    private static int first = 0;

    private FilterScreenSettings() {
        mLinearLayout = new LinearLayout(mContext);
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
    }

    private static void getInstance() {
        if (mLinearLayout == null || mWindowManager == null) {
            new FilterScreenSettings();
        }
    }

    public static void setAlpa(int alpa) {
        getInstance();
        if(first != 0){
            removeAlpa();
        }
        mLinearLayout = new LinearLayout(mContext);        
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        FilterScreenSettings.alpa = alpa;
        mLinearLayout.setBackgroundColor(getColor());
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                0 | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        mWindowManager.addView(mLinearLayout, params);
        first++;
    }
    public static void removeAlpa(){
        mWindowManager.removeView(mLinearLayout);
    }

    private static int getColor() {
        // TODO it stands for AlpaRGB
        String hex = String.format("%02x%02x%02x%02x", alpa, red, green, blue);
        Log.e("print", ""+alpa+""+""+red+"-"+green+"-"+blue+"");
        // TODO get numbers int radix of 10
        return (int) Long.parseLong(hex, 16);
    }

}
