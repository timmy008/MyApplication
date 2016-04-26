package com.timmy008.myapplication;

import android.app.Application;

/**
 * Created by Timmy on 2016/4/26.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}
