package com.bingkong.bknet.http.retrofit;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.media.AudioRouting;
import android.text.TextUtils;
import android.util.Log;

import com.bingkong.bknet.http.model.UserInfoModel;
import com.blankj.utilcode.util.StringUtils;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.json.JSONException;
import org.json.JSONObject;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 *Token 管理类
 */
public class TokenManager {
    public static int USERTYPE_GUEST_LOGIN=1;
    public static int USERTYPE_NORMAL=0;
    private String mToken;
    private UserInfoModel userInfoModel;
    private String baseUrl;
    public static final  String SP_USER_INFO="UserInfo";
    public static final String SP_BASE_URL="BaseUrl";
    public static final String SP_UUID_ROOT_PATH="UUIDPATHROOT";
    private static TokenManager sUserInfoManager = new TokenManager();
    static Context mContext = null;

    private TokenManager() {
    }

    public String getUUIDRootPath(){
        return SPUtils.getInstance().getString(SP_UUID_ROOT_PATH,"");
    }

    public void setUUIDRootPath(String rootPath){
        SPUtils.getInstance().put(SP_UUID_ROOT_PATH,rootPath);
    }

    public static void init(Context context){
        mContext = context;
    }

    /*
     * Check if the registered user is normal user.
     * */
    boolean mIsLoginByDeviceId = false;

    /*
    * Check if the registered user is normal user.
    * */
    public boolean IfNormalUser(){
        return getCurrentUserType()==USERTYPE_NORMAL;
    }
    //Only user register as formal userid, it will return true,(login with email address)
    //else alwayes return false.(register as guest or no token)
    public boolean IfUserLogined(){
        if (((StringUtils.isEmpty(mToken))||
                (userInfoModel==null)||
                (userInfoModel.getUserInfo()==null))&&
                getToken()!="TEST_ACCESS_TOKEN") {
            return false;
        } else {
            return true;
        }
    }

    public boolean isAdminUser(){
        if(!IfUserLogined()){
            return false;
        } else {
            return 2 == userInfoModel.getUserInfo().getUserType();
        }
    }

    /*
    * This function will return the current user's type.
    * */
    public int getCurrentUserType(){
        if(!IfUserLogined()){
            return USERTYPE_GUEST_LOGIN;
        } else {
            return userInfoModel.getUserInfo().getUserType();
        }
    }

    public static TokenManager getInstance() {
        return sUserInfoManager;
    }

    public void initOnApplicationCreate(String defaultBaseUrl) {
        baseUrl= (String) SPUtils.getInstance().getString(SP_BASE_URL,defaultBaseUrl);
//        Log.e("TokenManager", "initOnApplicationCreate: baseUrl is "+baseUrl);
//        String userJosn= (String) SPUtils.getInstance().getString(SP_USER_INFO,"");
//        if (!TextUtils.isEmpty(userJosn)) {
//            userInfoModel = new Gson().fromJson(userJosn,UserInfoModel.class);
//        } else {
//            userInfoModel=null;
//        }
    }

    public void setBaseUrl(String newbaseUrl) {
        SPUtils.getInstance().put(SP_BASE_URL, newbaseUrl);
        baseUrl= newbaseUrl; //(String) SPUtils.getInstance().getString(SP_BASE_URL,"");
        Log.e("setBaseUrl", "now BaseUrl: is "+baseUrl );
    }

    public  String getSpBaseUrl() {
        return SPUtils.getInstance().getString(SP_BASE_URL, baseUrl);
    }

    public UserInfoModel getUserInfoModel() {
        return userInfoModel;
    }

    public void setUserInfoModel(UserInfoModel userInfoModel) {
        this.userInfoModel = userInfoModel;
        if (null!=userInfoModel) {
            SPUtils.getInstance().put(SP_USER_INFO, new Gson().toJson(userInfoModel));
        }else {
            SPUtils.getInstance().remove(SP_USER_INFO);
        }
    }

    public String getToken() {
        if (TextUtils.isEmpty(mToken)){
            return "TEST_TOKEN";
        }else {
            return mToken;
        }
    }

    public void clearToken() {
        SPUtils.getInstance().remove("yfwCookie");
    }

    public static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static Boolean isTokenProblem(String messageFromServer){
        if(StringUtils.isEmpty(messageFromServer)) {
            return false;
        } else {
            return messageFromServer.contains("Token expired") ||
                    messageFromServer.contains("Token invalid") ||
                    messageFromServer.contains("Missing token") ||
                    messageFromServer.contains("Missing absent") ||
                    messageFromServer.contains("refreshToken expired")||(
                            messageFromServer.contains("Forbidden"));
        }
    }
}
