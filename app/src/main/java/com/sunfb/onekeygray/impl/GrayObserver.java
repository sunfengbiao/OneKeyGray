package com.sunfb.onekeygray.impl;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;


public class GrayObserver implements Observer {

    @Override
    public void update() {
        GrayController.getInStance().setState(true);
        Activity currentActivity = ActivityDynamicAccumulator.get().topActivity();
        Intent oneKeyGrayIntent = new Intent(currentActivity, OnekeyGrayActivity.class);
        currentActivity.startActivity(oneKeyGrayIntent);
    }

    @Override
    public void open() {
        GrayController.getInStance().setState(true);
        Activity currentActivity = ActivityDynamicAccumulator.get().topActivity();
        Intent oneKeyGrayIntent = new Intent(currentActivity, OnekeyGrayActivity.class);
        currentActivity.startActivity(oneKeyGrayIntent);
    }

    @Override
    public void close() {
        GrayController.getInStance().setState(false);
        Activity currentActivity = ActivityDynamicAccumulator.get().topActivity();
        Intent oneKeyGrayIntent = new Intent(currentActivity, OnekeyGrayActivity.class);
        currentActivity.startActivity(oneKeyGrayIntent);
    }
}
