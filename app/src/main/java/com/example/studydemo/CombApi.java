package com.example.studydemo;

import com.bingkong.bkbase.model.LoginResponse;
import com.bingkong.bknet.http.retrofit.HttpResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CombApi {
    public static final String APITAG_USERLOGIN = "userLogin";

    @POST("wxapp.login.loginByMobiletest")
//    Observable<Response<LoginResponse>> phoneLogin(@Query("appId") String app_id,@Query("appKey") String app_key,@Query("loginId") String loginId);
    Observable<Response<HttpResponse<LoginResponse.ResultBean>>> phoneLogin(@Query("appId") String app_id, @Query("appKey") String app_key, @Query("loginId") String loginId);


}