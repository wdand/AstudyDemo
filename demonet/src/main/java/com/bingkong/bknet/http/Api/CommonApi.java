package com.bingkong.bknet.http.Api;
import com.bingkong.bknet.http.model.CreateShareModel;
import com.bingkong.bknet.http.model.RetUploadImage;
import com.bingkong.bknet.http.retrofit.HttpResponse;


import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface CommonApi {
    @Multipart
    @POST("reports/appLog")
    Call<HttpResponse<Object>> reportAppLog(@Part() MultipartBody.Part logfile);


    @FormUrlEncoded
    @POST("upload")
    Call<HttpResponse<RetUploadImage>> uploadImage(@Field("photo") String base64,
                                                   @Field("sourceFileName") String fileName);

    @POST("/shares")
    Call<HttpResponse<CreateShareModel.Result>> createShare(@Body CreateShareModel.Req req);


}
