package com.bingkong.bknet.http.retrofit;
import android.annotation.SuppressLint;
import android.content.Context;
import android.media.session.MediaSession;
import android.util.Log;
import com.blankj.utilcode.util.LogUtils;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * RetrofitUtils工具类
 *
 * @author niejiahuan
 */
public class RetrofitUtils2 {
    /**
     * 接口地址
     */
//    static final String TEST = "https://unokiwidev.yotach.net/";
//    static final String RELEASE = "http://hz.chengmi.la/";

//    public static final String BASE_URL = TEST;
    public static final int CONNECT_TIME_OUT = 5;//连接超时时长x秒
    public static final int READ_TIME_OUT = 25;//读数据超时时长x秒
    public static final int WRITE_TIME_OUT = 25;//写数据接超时时长x秒
    private static RetrofitUtils2 mInstance = null;
    Context mContext;
    private RetrofitUtils2(Context context) {
        mContext=context;
    }

    public static RetrofitUtils2 get(Context context) {
        if (mInstance == null) {
            synchronized (RetrofitUtils2.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitUtils2(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 设置okHttp
     *
     * @author niejiahuan
     */
    private  OkHttpClient okHttpClient() {
        //开启Log
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtils.e("okHttp:" + message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
//        HTTPSUtils httpsUtils=new HTTPSUtils(mContext);
        Log.i("RetrofitUtils2", "okHttpClient create");

        OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(createSSLSocketFactory())
                .hostnameVerifier(new TrustAllHostnameVerifier())
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
//                .addInterceptor(logging)
//                .addInterceptor(new ChuckInterceptor(mContext).showNotification(true))
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
//                        builder.addHeader("platform", "1");
//                        if (null!= TokenManager.getInstance().getReftoken()&&
//                                !TokenManager.getInstance().getReftoken().isEmpty()) {
//                            LogUtils.e("Authorization :"+TokenManager.getInstance().getReftoken());
//
//                            builder.addHeader("Authorization","Bearer "+TokenManager.getInstance().getReftoken());
////                            builder.addHeader("returntype", "json");
//                        } else {
//                            builder.addHeader("Authorization", "");
////                            builder.addHeader("returntype", "json");
//                        }
                        return chain.proceed(builder.build());
                    }
                })
                .build();
        return client;
    }

    /**
     * 获取Retrofit
     *
     * @author niejiahuan
     */
    public Retrofit retrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient())
                .baseUrl(TokenManager.getInstance().getSpBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }
    public Retrofit retrofit2() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient())
                .baseUrl(TokenManager.getInstance().getSpBaseUrl())
                .build();
        return retrofit;
    }
    /**
     * 默认信任所有的证书
     *
     * @return
     */
    @SuppressLint("TrulyRandom")
    private static SSLSocketFactory createSSLSocketFactory() {

        SSLSocketFactory sSLSocketFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllManager()},
                    new SecureRandom());
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return sSLSocketFactory;
    }

    private static class TrustAllManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)

                throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}
