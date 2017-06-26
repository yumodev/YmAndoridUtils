package com.yumo.sample;

import android.app.Application;

import com.yumo.common.android.YmContext;

/**
 * Created by yumodev on 17/3/9.
 */

public class YmApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        YmContext.getInstance().setAppContext(this);
    }
}
