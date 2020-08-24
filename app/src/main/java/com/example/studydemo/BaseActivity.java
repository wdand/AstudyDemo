package com.example.studydemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.studydemo.clipUserIcon.LifeCycleListener;
import com.youth.banner.util.LogUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import pub.devrel.easypermissions.EasyPermissions;

public abstract class BaseActivity extends Activity
        implements EasyPermissions.PermissionCallbacks,BaseView{

    protected Context mContext;
    protected Unbinder unBinder;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActivityStackManager.getManager().push(this);
        setContentView(getLayoutId());
        mContext = this;
        unBinder = ButterKnife.bind(this);
        LogUtils.d("BaseActivity---" + getClass().getSimpleName());
        setStatusBar();
        initBus();
        onViewCreated();
        initData(savedInstanceState);
        setListener();
//        SwipeBackHelper.onCreate(this);
//        SwipeBackHelper.getCurrentPage(this)//获取当前页面
//                .setSwipeBackEnable(isSwipeBackEnable())//设置是否可滑动
//                .setScrimColor(Color.TRANSPARENT);//底层阴影颜色;
    }

    protected boolean isSwipeBackEnable() {
        return false;
    }

    protected void onViewCreated(){}
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
//        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (mListener != null) {
            mListener.onStart();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mListener != null) {
            mListener.onRestart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mListener != null) {
            mListener.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mListener != null) {
            mListener.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mListener != null) {
            mListener.onStop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mListener != null) {
            mListener.onDestroy();
        }
        //移除view绑定
        if (unBinder != null) {
            unBinder.unbind();
        }
//        SwipeBackHelper.onDestroy(this);
//        ActivityStackManager.getManager().remove(this);
    }
    /**
     * 回调函数
     */
    public LifeCycleListener mListener;

    public void setOnLifeCycleListener(LifeCycleListener listener) {
        mListener = listener;
    }

    //设置状态栏
    public void setStatusBar(){
        //Android6.0（API 23）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(android.R.color.white));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}
