package com.example.testbrightness;

import android.hardware.Camera;

public class TorchSettings extends SettingsManager{
   private static Camera mCamera;
   private static boolean isLight = true;
   private TorchSettings(){
       super();
       mCamera = Camera.open();
   }
   private static void getInstance(){
       if(mCamera == null ){
          new TorchSettings();
       }
   }
   private static void changed(){
       try{
           getInstance();
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
       }catch(Exception ex){
           ex.printStackTrace();
       }
   }
   public static void setisLight(boolean isLight){
       TorchSettings.isLight = isLight;
       changed();
   }
}
