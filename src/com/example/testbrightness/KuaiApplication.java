package com.example.testbrightness;

import android.app.Application;
import android.util.Log;

public class KuaiApplication extends Application{
    public static Application mApplication;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        mApplication = this;
        Log.e("print", "Application");
    }
    

}
