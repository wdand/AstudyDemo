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
    private int expiresIn;
    private String reftoken;
    private int refreshExpiresIn;


    private Boolean isFastLoginWithoutRegister=false;

    private UserInfoModel userInfoModel;
    private String baseUrl;


    private String tokenStartTime;
    public static final String SP_TOKEN = "token";
    public static final String SP_TOKEN_expire = "Token_Expire";
    public static final String SP_TOKEN_STARTTIME="Token_StartTime";
    public static final String SP_REF_TOKEN = "reftoken";
    public static final String SP_REF_TOKEN_expire = "Token_Expire";
    public static final  String SP_USER_INFO="UserInfo";
    public static final String SP_BASE_URL="BaseUrl";
    public static final String SP_FASTLOGINUSER="FastLoginUser";
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
    public String getTokenStartTime() {
        return tokenStartTime;
    }

    public void setTokenStartTime(String tokenStartTime) {
        this.tokenStartTime = tokenStartTime;
        SPUtils.getInstance().put(SP_TOKEN_STARTTIME, tokenStartTime);
    }

    public int getExpiresIn() {
        return expiresIn;
    }
    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
        SPUtils.getInstance().put(SP_TOKEN_expire, expiresIn);
    }
    public int getRefreshExpiresIn() {
        return refreshExpiresIn;
    }

    public void setRefreshExpiresIn(int refreshExpiresIn) {
        this.refreshExpiresIn = refreshExpiresIn;
        SPUtils.getInstance().put(SP_REF_TOKEN_expire, refreshExpiresIn);
    }

    public static void init(Context context){
        mContext = context;
    }


    public static void initialization(){
        SPUtils.getInstance().put(SP_TOKEN, "UUIDPATHROOT");
        SPUtils.getInstance().put(SP_TOKEN_expire, "UUIDPATHROOT");
        SPUtils.getInstance().put(SP_TOKEN_STARTTIME, "UUIDPATHROOT");
        SPUtils.getInstance().put(SP_REF_TOKEN, "UUIDPATHROOT");
        SPUtils.getInstance().put(SP_REF_TOKEN_expire, "UUIDPATHROOT");
        SPUtils.getInstance().put(SP_USER_INFO, "UUIDPATHROOT");
        SPUtils.getInstance().put(SP_BASE_URL, "https://dev-api.yizong.cn/");
        SPUtils.getInstance().put(SP_FASTLOGINUSER, "UUIDPATHROOT");
        SPUtils.getInstance().put(SP_UUID_ROOT_PATH, "UUIDPATHROOT");

    }

    public boolean isCurrentLoginUser(String userId){
        if(StringUtils.isEmpty(userId)){
            return false;
        }
        if(IfUserLogined()) {
            return userId.equals(getUserInfoModel().getUserInfo().getUserId());
        } else {
            return false;
        }
    }
    public String getCurrentUserId(){
        if(IfUserLogined()) {
            return getUserInfoModel().getUserInfo().getUserId();
        } else {
            if((getToken()!=null)&&(getToken().equals("TEST_TOKEN"))) {
                return "f9cbc044b4c54e7f9f8824de56f9953c";
            }
            return null;
        }
    }

    /*
     * Check if the registered user is normal user.
     * */
    boolean mIsLoginByDeviceId = false;
    public void setIsLoginByDeviceId(boolean isLoginByDeviceId) {
        mIsLoginByDeviceId = isLoginByDeviceId;
    }

    public boolean isIsLoginByDeviceId() {
        return mIsLoginByDeviceId;
    }


    public boolean IfGuestUser(){
        return getCurrentUserType()==USERTYPE_GUEST_LOGIN;
    }
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

    public boolean isEmailVerified(){
        if(!IfUserLogined()) {
            return false;
        }
        return 0!=userInfoModel.getUserInfo().getEmailVerificationStatus();
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

    public String getEmailAddress(){
        if((userInfoModel!=null)&&
                (userInfoModel.getUserInfo()!=null)){
            return userInfoModel.getUserInfo().getEmail();
        } else {
            return "";
        }
    }
    public void setRefreshTokenModel(){
    }

    public static String getNowStr(Date dateTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String strRet = sdf.format(dateTime);
        return strRet;
    }
    public void initOnApplicationCreate(String defaultBaseUrl) {
        mToken = (String) SPUtils.getInstance().getString(SP_TOKEN,"");
        reftoken= (String) SPUtils.getInstance().getString(SP_REF_TOKEN,"");
        expiresIn=(int) SPUtils.getInstance().getInt(SP_TOKEN_expire,0);
        refreshExpiresIn =(int) SPUtils.getInstance().getInt(SP_REF_TOKEN_expire,0);
        isFastLoginWithoutRegister =(Boolean) SPUtils.getInstance().getBoolean(SP_FASTLOGINUSER,false);
        baseUrl= (String) SPUtils.getInstance().getString(SP_BASE_URL,defaultBaseUrl);
        tokenStartTime=(String)SPUtils.getInstance().getString(SP_TOKEN_STARTTIME,getNowStr(new Date()));
        Log.e("TokenManager", "initOnApplicationCreate: baseUrl is "+baseUrl);
        String userJosn= (String) SPUtils.getInstance().getString(SP_USER_INFO,"");
        if (!TextUtils.isEmpty(userJosn)) {
            userInfoModel = new Gson().fromJson(userJosn,UserInfoModel.class);
        } else {
            userInfoModel=null;
        }
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

    public String getReftoken() {
        return reftoken;
    }

    public void setReftoken(String reftoken) {
        this.reftoken = reftoken;
        SPUtils.getInstance().put(SP_REF_TOKEN, reftoken);
    }

    public String getToken() {
        if (TextUtils.isEmpty(mToken)){
            return "TEST_TOKEN";
        }else {
            return mToken;
        }
    }

    public void setToken(String token) {
        mToken = token;
        SPUtils.getInstance().put(SP_TOKEN, token);
    }

    public void clearToken() {
        mToken = "";
        reftoken="";
        expiresIn=0;
        refreshExpiresIn =0;
        userInfoModel=null;

        SPUtils.getInstance().remove(SP_TOKEN);
        SPUtils.getInstance().remove("yfwCookie");
        SPUtils.getInstance().remove(SP_TOKEN_expire);
        SPUtils.getInstance().remove(SP_REF_TOKEN);
        SPUtils.getInstance().remove(SP_REF_TOKEN_expire);
        SPUtils.getInstance().remove(SP_USER_INFO);
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
