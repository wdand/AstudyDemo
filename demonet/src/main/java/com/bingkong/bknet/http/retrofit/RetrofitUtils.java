package com.bingkong.bknet.http.retrofit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
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
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * RetrofitUtils工具类
 *
 * @author niejiahuan
 */
public class RetrofitUtils {

    public static final String BASE_URL = TokenManager.getInstance().getSpBaseUrl();
    public static final int CONNECT_TIME_OUT = 5;//连接超时时长x秒
    public static final int READ_TIME_OUT = 25;//读数据超时时长x秒
    public static final int WRITE_TIME_OUT = 25;//写数据接超时时长x秒
    private static RetrofitUtils mInstance = null;
    Context mContext;
    private RetrofitUtils(Context context) {
        mContext=context;
    }

    public static RetrofitUtils get(Context context) {
        if (mInstance == null) {
            synchronized (RetrofitUtils.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitUtils(context);
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
        Log.i("RetrofitUtils", "okHttpClient create");
        OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(createSSLSocketFactory())
                .hostnameVerifier(new TrustAllHostnameVerifier())
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
//               .addInterceptor(new GzipRequestInterceptor())    //server not support this morment.
//                .addInterceptor(logging)   //debug only
//              .addInterceptor(new ChuckInterceptor(mContext).showNotification(true))
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        String cookie= (String) SPUtils.getInstance().getString("yfwCookie","");

                        Request.Builder builder = chain.request().newBuilder();
                        builder.addHeader("Content-Type", "application/json");
                        builder.addHeader("Cookie", cookie);
                        return chain.proceed(builder.build());
                    }
                })
                .build();
        return client;
    }

    public static class NullOnEmptyConverterFactory extends Converter.Factory {

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
            return new Converter<ResponseBody,Object>() {
                @Override
                public Object convert(ResponseBody body) throws IOException {
                    if (body.contentLength() == 0) {
                        Log.e(TAG, "convert: meet zero length body" );
                    }
                    return delegate.convert(body);
                }
            };
        }
    }
    /**
     * 获取Retrofit
     *
     * @author niejiahuan
     */
    public Retrofit retrofit() {
        String s = TokenManager.getInstance().getSpBaseUrl();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient())
                .baseUrl(TokenManager.getInstance().getSpBaseUrl())
//                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }
    public Retrofit retrofit2() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient())
                .baseUrl(BASE_URL)
                .build();
        return retrofit;
    }
    /**
     * 默认信任所有的证书
     *
     * @return
     */
    @SuppressLint("TrulyRandom")
    private static javax.net.ssl.SSLSocketFactory createSSLSocketFactory() {

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
