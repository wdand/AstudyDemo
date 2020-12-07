package com.bingkong.bknet.http.Api;
import android.content.Context;
import android.util.ArrayMap;
import android.util.Log;

import com.bingkong.bknet.http.retrofit.RetrofitUtils;
import com.bingkong.bknet.http.retrofit.RetrofitUtils2;
import com.bingkong.bknet.http.retrofit.TokenManager;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class ServiceManager {
    static Context mContext;
    private static HashMap<Class,String> mTokens=new HashMap<>();
    public static void initServiceManager(Context context){
        mContext=context;
    }
    private static final ArrayMap<Class, Object> mServiceMap = new ArrayMap<>();
    public static void clearMap(){
        mServiceMap.clear();
    }
    public static <T> T create(Class<T> serviceClass) {
        Object service = mServiceMap.get(serviceClass);
        try {
            if (service == null) {
                service = RetrofitUtils.get(mContext).retrofit().create(serviceClass);
                mTokens.put(serviceClass, TokenManager.getInstance().getToken());
                mServiceMap.put(serviceClass, service);
            }
        } catch (Exception e){
            Log.e(TAG, "create: error happen" );
        }

        return (T) service;
    }
    public static <T> String getUsedToken(Class<T> serviceClass){
        return mTokens.get(serviceClass);
    }
    public static <T> T create2(Class<T> serviceClass) {
        Object service = mServiceMap.get(serviceClass);
        if (service == null) {
            service = RetrofitUtils2.get(mContext).retrofit().create(serviceClass);
            mTokens.put(serviceClass, TokenManager.getInstance().getToken());
            mServiceMap.put(serviceClass, service);
        }
        return (T) service;
    }
    public static <T> T defCreate(){
        return (T) create(ApiService.class);
    }
}
