
package com.example.testbrightness;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;

public class TorchActivity extends Activity {
    private Camera mCamera;
    private boolean isLight = false;

    @Override
    protected void onCreate(Bundle budle) {
        // TODO Auto-generated method stub
        super.onCreate(budle);
        mCamera = Camera.open();
        getisLight();
    }

    private void swtichTouch() {
        try {
            final Camera.Parameters params = mCamera.getParameters();
            if (params != null) {
                if (isLight) {
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    mCamera.setParameters(params);
                } else {
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    mCamera.setParameters(params);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getisLight() {
        Intent intent = getIntent();
        isLight = intent.getBooleanExtra("isLight", false);
        swtichTouch();
    }

}
