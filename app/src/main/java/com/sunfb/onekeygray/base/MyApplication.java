package com.sunfb.onekeygray.base;

import android.app.Application;

import com.sunfb.onekeygray.impl.ActivityDynamicAccumulator;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActivityDynamicAccumulator.get().registerSelf(this);
    }

}
