package com.bingkong.bkbase;




import com.bingkong.bknet.http.retrofit.HttpResponse;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginApi {
    public static final String APITAG_YFW_LOGIN = "yfwLogin";

    @POST("guest.account.login")
    Observable<Response<HttpResponse<YFWLoginInfoRes.ResultBean>>> yfwLogin(@Query("userName") String userName, @Query("password") String password);
}