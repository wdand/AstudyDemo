package com.example.studydemo;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import com.alibaba.android.arouter.launcher.ARouter;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.bingkong.bkbase.app.ComApp;
import com.bingkong.bkbase.app.XBaseApp;
import com.bingkong.bkbase.sharepf.CommonSharePf;
import com.bingkong.bkbase.utils.CrashRemoteHandle;
import com.bingkong.bkbase.utils.LogUtils;
import com.bingkong.bknet.http.retrofit.TokenManager;
import com.blankj.utilcode.util.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshInitializer;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.imsdk.v2.V2TIMCallback;
import com.tencent.imsdk.v2.V2TIMManager;
import com.tencent.imsdk.v2.V2TIMSDKConfig;
import com.tencent.imsdk.v2.V2TIMSDKListener;
import com.uc.crashsdk.export.CrashApi;
import com.umeng.commonsdk.UMConfigure;

import static com.bingkong.bknet.http.retrofit.TokenManager.SP_BASE_URL;

/**
 * 主Application，所有百度定位SDK的接口说明请参考线上文档：http://developer.baidu.com/map/loc_refer/index.html
 * <p>
 * 百度定位SDK官方网站：http://developer.baidu.com/map/index.php?title=android-locsdk
 * <p>
 * 直接拷贝com.baidu.location.service包到自己的工程下，简单配置即可获取定位结果，也可以根据demo内容自行封装
 */
public class LocationApplication extends ComApp {
    public LocationService locationService;
    public Vibrator mVibrator;

    private static Context context;
    private static final String TAG = "LocationApplication";

    static {
        //启用矢量图兼容
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        //设置全局默认配置（优先级最低，会被其他设置覆盖）
        SmartRefreshLayout.setDefaultRefreshInitializer(new DefaultRefreshInitializer() {
            @Override
            public void initialize(@NonNull Context context, @NonNull RefreshLayout layout) {
                //全局设置（优先级最低）
                layout.setEnableAutoLoadMore(true);
                layout.setEnableOverScrollDrag(false);
                layout.setEnableOverScrollBounce(true);
                layout.setEnableLoadMoreWhenContentNotFull(true);
                layout.setEnableScrollContentWhenRefreshed(true);
//                  layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            }
        });
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                //全局设置主题颜色（优先级第二低，可以覆盖 DefaultRefreshInitializer 的配置，与下面的ClassicsHeader绑定）
//                    layout.setPrimaryColorsId(android.R.color.transparent, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);
            }
        });

        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }


    public static Context getContext() {
        return context;
    }

    public static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setDeBug(true);
        context = getApplicationContext();
        String baseUrl;
        ARouter.init(this);
        UMConfigure.setLogEnabled(true);
//        UMConfigure.preInit(this,"632698c388ccdf4b7e30d070",BuildConfig.packageChannel);
        UMConfigure.init(context, "632698c388ccdf4b7e30d070", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        final Bundle customInfo = new Bundle();
        customInfo.putBoolean("mCallNativeDefaultHandler",true);
        CrashApi.getInstance().updateCustomInfo(customInfo);
        /***
         * 初始化定位sdk
         */
//        TokenManager.getInstance().initialization();
        locationService = new LocationService(getApplicationContext());
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());
        SDKInitializer.setCoordType(CoordType.BD09LL);
        Context context = getApplicationContext();
        baseUrl = context.getResources().getString(R.string.my_profile);
        SPUtils spUtils = SPUtils.getInstance();

        LogUtils.logd("----printBaseUrl---", SPUtils.getInstance().getString(SP_BASE_URL, "---"));
        String savedUrl = (String) SPUtils.getInstance().getString(SP_BASE_URL, baseUrl);
        TokenManager.getInstance().initOnApplicationCreate(baseUrl);
        CrashRemoteHandle.getInstance().init(getApplicationContext());

    }


}

