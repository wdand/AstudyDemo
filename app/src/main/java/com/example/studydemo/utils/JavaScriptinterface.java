package com.example.studydemo.utils;

import android.app.Activity;
import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class JavaScriptinterface {
    Context context;
    WeakReference<Activity> act;
    public JavaScriptinterface(Activity activity) {
        this.context= activity;
        this.act = new WeakReference<Activity>(activity);
    }


    // 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    public void androidFunction(String msg) {
        System.out.println("JS调用了Android原生的的androidFunction方法");
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
