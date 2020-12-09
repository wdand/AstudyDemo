package com.example.studydemo;

import com.bingkong.bkbase.model.LoginResponse;
import com.bingkong.bknet.http.retrofit.HttpResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CombApi {
    public static final String APITAG_USERLOGIN = "userLogin";
    public static final String APITAG_CASEHISTORY = "caseHistory";
    public static final String APITAG_YFW_LOGIN = "yfwLogin";
    public static final String APITAG_GETORDER = "yfwGetOrder";

    @POST("wxapp.login.loginByMobiletest")
//    Observable<Response<LoginResponse>> phoneLogin(@Query("appId") String app_id,@Query("appKey") String app_key,@Query("loginId") String loginId);
    Observable<Response<HttpResponse<LoginResponse.ResultBean>>> phoneLogin(@Query("appId") String app_id, @Query("appKey") String app_key, @Query("loginId") String loginId);

    @POST("wxapp.cure.case.getCaseHistoryListV4")
    Observable<Response<HttpResponse<CaseDemoBean.ResultBean>>> getCaseHistoryListV(@Query("start") Integer start, @Query("limit") String limit);

    @POST("guest.account.login")
    Observable<Response<HttpResponse<YFWLoginInfo.ResultBean>>> yfwLogin(@Query("userName") String userName, @Query("password") String password);

    @POST("person.order.getPageData")
    Observable<Response<HttpResponse<OrderInfo.ResultBean>>> getAllOrder(@Query("pageIndex") Integer pageIndex, @Query("pageSize") Integer pageSize);

}