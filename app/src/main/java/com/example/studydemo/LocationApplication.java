package com.example.studydemo;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Vibrator;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.bingkong.bkbase.utils.CrashRemoteHandle;
import com.bingkong.bkbase.utils.LogUtils;
import com.bingkong.bknet.http.retrofit.TokenManager;
import com.blankj.utilcode.util.SPUtils;

import static com.bingkong.bknet.http.retrofit.TokenManager.SP_BASE_URL;

/**
 * 主Application，所有百度定位SDK的接口说明请参考线上文档：http://developer.baidu.com/map/loc_refer/index.html
 *
 * 百度定位SDK官方网站：http://developer.baidu.com/map/index.php?title=android-locsdk
 *
 * 直接拷贝com.baidu.location.service包到自己的工程下，简单配置即可获取定位结果，也可以根据demo内容自行封装
 */
public class LocationApplication extends Application {
    public LocationService locationService;
    public Vibrator mVibrator;
    @Override
    public void onCreate() {
        super.onCreate();
        /***
         * 初始化定位sdk，建议在Application中创建
         */
        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());
        SDKInitializer.setCoordType(CoordType.BD09LL);
        String baseUrl;
        Context context=getApplicationContext();

            baseUrl =context.getResources().getString(R.string.my_profile);


//        String savedUrl= (String) SPUtils.getInstance().getString(SP_BASE_URL,
//                LogUtils.logd("----printBaseUrl---", SPUtils.getInstance().getString(SP_BASE_URL,"---") );baseUrl);

        TokenManager.getInstance().initOnApplicationCreate(baseUrl);
        CrashRemoteHandle.getInstance().init(getApplicationContext());
    }

}

