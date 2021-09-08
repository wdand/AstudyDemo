package com.example.studydemo.webview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.studydemo.ArouterConstants;
import com.example.studydemo.R;
import com.example.studydemo.utils.JavaScriptinterface;

@Route(path = ArouterConstants.WEBVIEW_ACT)
public class WebViewActivity extends AppCompatActivity {
     private  WebView mWebView;
     private  Button calljsbyloadurl;
     private  Button calljsbyevaluateJs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWebView =(WebView) findViewById(R.id.webviewtest);
        WebSettings webSettings = mWebView.getSettings();
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 通过addJavascriptInterface()将Java对象映射到JS对象
        //参数1：Javascript对象名
        //参数2：Java对象名
        mWebView.addJavascriptInterface(new JavaScriptinterface(this), "test");//AndroidtoJS类对象映射到js的test对象

        mWebView.loadUrl("file:///android_asset/index.html");

        calljsbyloadurl = (Button) findViewById(R.id.calljsbyloadurl);
        calljsbyevaluateJs = (Button) findViewById(R.id.calljsbyevaluateJs);


        calljsbyloadurl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 通过Handler发送消息
                mWebView.post(new Runnable() {
                    @Override
                    public void run() {
                        // 注意调用的JS方法名要对应上
                        // 调用javascript的getPasswd()方法
                        mWebView.loadUrl("javascript:getPasswd()");
                    }
                });

            }
        });

        calljsbyevaluateJs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.evaluateJavascript("javascript:getPasswd()", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        //此处为 js 返回的结果
                        Log.e("onReceiveValue", "onReceiveValue: "+value);
                        Toast.makeText(WebViewActivity.this, value, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // 由于设置了弹窗检验调用结果,所以需要支持js对话框
        // webview只是载体，内容的渲染需要使用webviewChromClient类去实现
        // 通过设置WebChromeClient对象处理JavaScript的对话框
        //设置响应js 的Alert()函数
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(WebViewActivity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }

        });


    }

}