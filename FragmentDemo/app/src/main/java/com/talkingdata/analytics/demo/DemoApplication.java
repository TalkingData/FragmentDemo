package com.talkingdata.analytics.demo;

import com.tendcloud.tenddata.TCAgent;

import android.app.Application;

public class DemoApplication extends Application{

    @Override
    public void onCreate() {
        TCAgent.setReportUncaughtExceptions(true);
        TCAgent.init(this);
        super.onCreate();
    }

}
