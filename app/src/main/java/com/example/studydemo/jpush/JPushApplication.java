package com.example.studydemo.jpush;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

public class JPushApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true); //正式环境时去掉此行代码
        JPushInterface.init(this);
    }
}
