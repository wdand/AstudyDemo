package com.bingkong.bkbase.app;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

/**
 * Created by niejiahuan on 18/2/3.
 */

public class XBaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
