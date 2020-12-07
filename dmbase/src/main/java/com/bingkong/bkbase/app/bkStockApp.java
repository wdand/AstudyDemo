package com.bingkong.bkbase.app;

import android.content.Context;

public class bkStockApp {
    public static Context appContext;

    public static void init(Context context) {
        appContext = context;
    }

    public static Context getContext() {
        return appContext;
    }
}
