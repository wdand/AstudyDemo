package com.bingkong.bknet.http.function;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.bingkong.bknet.http.exception.ServerException;
import com.bingkong.bknet.http.retrofit.HttpResponse;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import okhttp3.Headers;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * 服务器结果处理函数
 *
 * @author niejiahuan
 */
public class ServerResultFunction implements Function<Response<HttpResponse>, Object> {
    @Override
    public Object apply(@NonNull Response<HttpResponse> response) throws Exception {
        //打印服务器回传结果
        LogUtils.e(response.toString());
        if(response.toString().indexOf("guest.account.login") > -1){//代表包含wxapp.login.loginByMobiletest
            Headers headers =  response.headers();
            String cookie = headers.get("set-cookie");
            SPUtils.getInstance().put("yfwCookie", cookie);
        }
            if (response.body() != null) {
//                return response.body();
                if (response.body().getResult()==null){
                    if(response.raw().request().url().toString().contains("register")) {
                        if(!response.body().getMsg().equals("success")){
                            throw new ServerException(response.code(), response.body().getMsg());
                        }
                    }
                    if (response.body().getCode() == -999){
                       return response.body();
                    }
                    return new Object();
                }
                else {
                    return response.body().getResult();
                }
            }else{
                String json = response.errorBody().string();
                if (!TextUtils.isEmpty(json)){
                    if(json.contains("502")) {
                        Log.d(TAG, "apply: there may a error!");
                    }
                    HttpResponse httpResponse=null;
                    int errCode=5006;
                    String errorMessage="unkown error";
                    boolean ifNeedThrowError=false;
                    try {
                        httpResponse= new Gson().fromJson(json, HttpResponse.class);
                        if (httpResponse != null) {
                            throw new ServerException(httpResponse.getCode(), httpResponse.getMsg());
                        } else {
                            throw new ServerException(5005, "Empty response from server,");
                        }
                    } catch (Exception e) {
                        //e.printStackTrace();
                        ifNeedThrowError=true;
                        if(e instanceof ServerException) {
                            ServerException se=(ServerException) e;
                            errCode=se.getCode();
                            errorMessage=se.getMsg();
                        } else {
                            errCode=5006;
                            errorMessage="unkown error";
                        }
                    }

                    throw new ServerException(errCode, errorMessage);

                }else {
                    throw new ServerException(response.code(), response.message());
                }

            }
    }
}