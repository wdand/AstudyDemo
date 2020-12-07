package com.bingkong.bkbase.utils;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import com.bingkong.bknet.http.Api.CommonApi;
import com.bingkong.bknet.http.Api.ServiceManager;
import com.bingkong.bknet.http.model.CreateShareModel;
import com.bingkong.bknet.http.retrofit.HttpResponse;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Response;

public class Share {
    public interface ShareCallback {
        void onCreateShareIdFinish(CreateShareModel.Result result);
    }

    private static String TAG = "share";

    public static SpannableString getUserNameAndTime(Context context, int colorid,
                                                     String firstname,
                                                     String lastname,
                                                     String username,
                                                     String strtime) {
        String Name1seg = firstname + " " + lastname;
        SpannableString spannableString =
                new SpannableString(Name1seg + " @" + username + " - " + strtime);
        spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(colorid)),
                0, Name1seg.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),
                0,
                Name1seg.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    public static void reqShareData(final CreateShareModel.Req req,
                                    final ShareCallback shareCallback) {
        LogUtils.logd(TAG, "reqShareData: " + req.toString());
        CommonApi api = ServiceManager.create(CommonApi.class);
        final Call<HttpResponse<CreateShareModel.Result>> caller =
                (Call<HttpResponse<CreateShareModel.Result>>) api.createShare(req);
        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    try {
                        Response<HttpResponse<CreateShareModel.Result>> resps = caller.execute();
                        LogUtils.loge(TAG, "doInBackground: " + resps.isSuccessful());
                        if ((resps != null) && (resps.body() != null) && (shareCallback != null)) {
                            CreateShareModel.Result result = (CreateShareModel.Result) resps.body().getResult();
                            shareCallback.onCreateShareIdFinish(result);
                        }
                    } catch (Exception e) {
                        LogUtils.loge(TAG, "doInBackground: " + e.getMessage());
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                }
            }.executeOnExecutor((ExecutorService) Executors.newFixedThreadPool(3));
        } catch (Exception e) {
            LogUtils.loge(TAG, "postReport: " + e.getMessage());
        }
        return;
    }

    /*
     * 分享图片和文字内容
     * @param dlgTitle 分享对话框标题
     * @param subject  主题
     * @param content  分享内容（文字）
     * @param uri      图片资源URI
     */
    public static Intent getShareIntent(CreateShareModel.Result result) {
        if (result == null) {
            return null;
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
//      intent.setType("image/*");
//      intent.putExtra(Intent.EXTRA_STREAM, result.getUrl());
        intent.setType("text/plain");
        //this.getContext().getContentResolver();
        intent.putExtra(Intent.EXTRA_TEXT, result.getUrl());
        //       if (subject != null && !"".equals(subject)) {
        //           intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        //       }
        return intent;
    }
}
