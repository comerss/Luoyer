package com.heimat.luoyer.http.retrofit;

import com.google.gson.GsonBuilder;
import com.heimat.luoyer.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by comerss on 2017/4/13.
 */
public class RetrofitHelper {
    public static volatile Retrofit mRetrofit;

    public static Retrofit retrofit() {
        if (mRetrofit == null) {
            synchronized (Retrofit.class) {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                if (BuildConfig.DEBUG) {
                    // Log信息拦截器
                    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    //设置 Debug Log 模式
                    builder.addInterceptor(loggingInterceptor);
                }

                builder.addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        builder.addHeader("User-Agent", "APP_Android_3.0");
                        builder.addHeader("Cache-Control", "max-age=0");
                        builder.addHeader("Upgrade-Insecure-Requests", "1");
                        builder.addHeader("X-Requested-With", "XMLHttpRequest");
                        return chain.proceed(builder.build());
                    }
                });

                OkHttpClient okHttpClient = builder.build();
                mRetrofit = new Retrofit.Builder()
                        .baseUrl(ApiService.API_SERVER_URL)//知乎的主机地址，其他需要直接给地址
                        .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .client(okHttpClient)
                        .build();
            }
        }
        return mRetrofit;
    }

    public static ApiService getApiService() {
        return retrofit().create(ApiService.class);
    }
}
