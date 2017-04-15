package com.heimat.luoyer.http.okhttp;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.heimat.luoyer.base.BaseBean;
import com.heimat.luoyer.http.Constant;
import com.heimat.luoyer.interfaces.HttpCallBack;
import com.heimat.luoyer.utils.LogUtil;
import com.heimat.luoyer.utils.SharedHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by code5 on 2017/4/14.
 * 所有的httpHelper网络请求都放在这个类里，便于管理维护和复用！
 */
public class OkHttpUtils {
    private static Gson mGson = new Gson();
    private static final int POST_REQUEST = 1;
    private static final int GET_REQUEST = 0;
    private volatile static OkHttpClient instance; //声明成 volatile
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static <T extends BaseBean> void doPost(final String url, Map<String,
            Object> map, final Class<T> clazz, final HttpCallBack<T> httpCallBack) {
        final String json = mGson.toJson(map);
        Observable.just(url)
                .map(new Func1<String, Map>() {
                    @Override
                    public Map call(String url) {
                        return getReponse(url, json, POST_REQUEST);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Map>() {
                    @Override
                    public void call(Map response) {
                        doResult(response, httpCallBack, clazz, url, json);
                    }
                });
    }

    public static <T extends BaseBean> void doPost(final String url, final String json, final Class<T> clazz, final HttpCallBack<T> httpCallBack) {
        Observable.just(url)
                .map(new Func1<String, Map>() {
                    @Override
                    public Map call(String url) {
                        return getReponse(url, json, POST_REQUEST);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Map>() {
                    @Override
                    public void call(Map response) {
                        doResult(response, httpCallBack, clazz, url, json);
                    }
                });
    }

    public static <T extends BaseBean> void doGet(final String url, final Class<T> clazz, final HttpCallBack<T> httpCallBack) {
        Observable.just(url)
                .map(new Func1<String, Map>() {
                    @Override
                    public Map call(String url) {
                        return getReponse(url, "", POST_REQUEST);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Map>() {
                    @Override
                    public void call(Map response) {
                        doResult(response, httpCallBack, clazz, url, "");
                    }
                });
    }

    private static <T extends BaseBean> void doResult(Map response, HttpCallBack<T> httpCallBack, Class<T> clazz, String url, String json) {
        if (response != null) {
            if (TextUtils.equals(String.valueOf(response.get("code")), "200")) {
                String result = response.get("body").toString();
                LogUtil.i("请求:", url + "\n" + json);
                LogUtil.i("返回: ", result);
                httpCallBack.onSuccess(99, result, mGson.fromJson(result, clazz));
            } else {
                httpCallBack.onError(99,TextUtils.isEmpty(response.get("message").toString()) ? response.get("body").toString() : response.get("message").toString());
                LogUtil.i("请求:", url + "\n" + json);
                LogUtil.i("返回: ", "错误信息：" + response.get("message").toString());
            }
        }
    }


    private static Map getReponse(String url, String json, int requestType) {
        Map<String, Object> responsemap = new HashMap<String, Object>();
        RequestBody body = RequestBody.create(JSON, json);
        okhttp3.Request request = null;
        try {

            if (requestType == 0) {
                request = new okhttp3.Request.Builder().url(url)
                        .get()
                        .put(body)
                        .addHeader("Authorization", SharedHelper.getSetting("AccessToken"))
                        .addHeader("User-Agent", Constant.User_Agent)
                        .build();
            } else {
                request = new okhttp3.Request.Builder().url(url)
                        .post(body)
                        .addHeader("Authorization", SharedHelper.getSetting("AccessToken"))
                        .addHeader("User-Agent", Constant.User_Agent)
                        .build();
            }
            Response response = null;
            response = getInstance().newCall(request).execute();
            responsemap.put("code", response.code());
            responsemap.put("message", response.message() == null ? "" : response.message());//错误信息放在哪接口定，这只是我的
            responsemap.put("body", response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
            responsemap.put("code", "");
            responsemap.put("message", "Response is null");
            responsemap.put("body", "");
        }
        return responsemap;
    }

    public static OkHttpClient getInstance() {
        if (instance == null) {
            synchronized (OkHttpClient.class) {
                if (instance == null) {
                    instance = new OkHttpClient.Builder()
                            .connectTimeout(40, TimeUnit.SECONDS)
                            .readTimeout(60, TimeUnit.SECONDS)
                            .writeTimeout(60, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
        return instance;
    }

    static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            //这个chain里面包含了request和response，所以你要什么都可以从这里拿
            okhttp3.Request request = chain.request();
            long t1 = System.nanoTime();//请求发起的时间
            LogUtil.i("Post请求：", request.url() + "");
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();//收到响应的时间
            //这里不能直接使用response.body().string()的方式输出日志
            //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
            //个新的response给应用层处理
            return response;
        }
    }

    /**
     * 取消某个请求
     *
     * @param tag
     */
    public static void cancelTag(Object tag) {
        for (Call call : getInstance().dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : getInstance().dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }

    }

    /**
     * 取消所有请求
     */
    public static void cancelAllTag() {
        getInstance().dispatcher().cancelAll();
    }
}
