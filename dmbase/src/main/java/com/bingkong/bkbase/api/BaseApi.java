package com.bingkong.bkbase.api;

import com.bingkong.bkbase.upload.model.UploadModel;
import com.bingkong.bknet.http.retrofit.HttpResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface BaseApi {

    @FormUrlEncoded
    @POST("/upload/preprocess")
    Call<UploadModel> uploadImageStart(@FieldMap Map<String, Object> parm);

    @Multipart
    @POST("/upload/uploading")
    Call<UploadModel> uploadImageLoading(
            @Part MultipartBody.Part file,
            @Part("resource_ext") RequestBody name,
            @Part("chunk_total") RequestBody chunk_total,
            @Part("chunk_index") RequestBody chunk_index,
            @Part("group") RequestBody group,
            @Part("group_subdir") RequestBody group_subdir,
            @Part("locale") RequestBody locale,
            @Part("resource_hash") RequestBody resource_hash,
            @Part("resource_temp_basename") RequestBody resource_temp_basename);

    @FormUrlEncoded
    @POST("/upload/uploaded")
    Call<HttpResponse<UploadModel>> uploadImageEnd(@Field("savedPath") String path);
}
