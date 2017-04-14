package com.heimat.luoyer.http.okhttp;

import android.content.Context;
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
import okhttp3.CertificatePinner;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by ccl on 2016/3/23.
 * 基于okhttp 的封装  ，这个封装是基于我项目中
 * ------------------>太多的参数提交封装的！
 * 这个是独立可用的，但是有个缺点就是每次请求都需要重写，不能复用写过的请求，
 * 需要复用的话就需要写一个Manger类来管理所有的网络请求
 */
public class HttpHelper {
    private static Gson mGson = new Gson();
    private volatile static OkHttpClient instance; //声明成 volatile
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static <T extends BaseBean> void doPost(Context context, final int flag, final String url, Map<String,
            Object> map, final Class<T> clazz, final HttpCallBack<T> httpCallBack) {
        final String json=mGson.toJson(map);
//        final LoadingDialog loadingDialog = new LoadingDialog(context, R.style.task_progress_dialog);
//        loadingDialog.setCanceledOnTouchOutside(false);
//        loadingDialog.setCancelable(true);
//        loadingDialog.show();
        Observable.just(url)
                .map(new Func1<String, Map>() {
                    @Override
                    public Map call(String url) {
                        return getReponse(url,json,flag);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Map>() {
                    @Override
                    public void call(Map response) {
                        doResult(response, httpCallBack, flag, clazz,null,url,json);
                    }
                });
    }
    public static <T extends BaseBean> void doPost(Context context, final int flag, final String url, Map<String,
            Object> map, boolean showDialog, final Class<T> clazz, final HttpCallBack<T> httpCallBack) {

        final String json=mGson.toJson(map);
//        final LoadingDialog loadingDialog = new LoadingDialog(context, R.style.task_progress_dialog);
//        loadingDialog.setCanceledOnTouchOutside(false);
//        loadingDialog.setCancelable(true);
//        if(showDialog){
//            loadingDialog.show();
//        }
        Observable.just(url)
                .map(new Func1<String, Map>() {
                    @Override
                    public Map call(String url) {
                        return getReponse(url, json, flag);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Map>() {
                    @Override
                    public void call(Map response) {
                        doResult(response, httpCallBack, flag, clazz,null,url,json);
                    }
                });
    }
    public static <T extends BaseBean> void doPost(Context context, final int flag, final String url, final String json, boolean showDialog, final Class<T> clazz, final HttpCallBack<T> httpCallBack) {

//        final LoadingDialog loadingDialog = new LoadingDialog(context, R.style.task_progress_dialog);
//        loadingDialog.setCanceledOnTouchOutside(false);
//        loadingDialog.setCancelable(true);
//        if(showDialog){
//            loadingDialog.show();
//        }
        Observable.just(url)
                .map(new Func1<String, Map>() {
                    @Override
                    public Map call(String url) {
                        return getReponse(url, json, flag);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Map>() {
                    @Override
                    public void call(Map response) {
                        doResult(response, httpCallBack, flag, clazz,null,url,json);
                    }
                });
    }
    private static <T extends BaseBean> void doResult(Map response, HttpCallBack<T> httpCallBack, int flag, Class<T> clazz, Object loadingDialog, String url, String json) {
        if(response!=null){
            if(TextUtils.equals(String.valueOf(response.get("code")),"200")){
                String result= response.get("body").toString();
                LogUtil.i("POST请求:", url + "\n" + json);
                LogUtil.i("Post返回: ",result);
                httpCallBack.onSuccess(flag,result,mGson.fromJson(result,clazz));
            }else{
                httpCallBack.onError((Integer) response.get("code"),(String) response.get("message"));
                LogUtil.i("POST请求:", url + "\n" + json);
                LogUtil.i("Post返回: ","错误信息："+response.get("message").toString());
            }
        }
//        if(loadingDialog!=null&&loadingDialog.isShowing()){
//            loadingDialog.dismiss();
//        }
    }


    private static Map getReponse(String url, String json, int flag) {
        Map<String ,Object> responsemap = new HashMap<String ,Object>();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url)
                .post(body).tag(flag)
                .addHeader("Authorization", SharedHelper.getSetting("AccessToken"))
                .addHeader("User-Agent", Constant.User_Agent)
                .build();
        Response response = null;
        try {
            response = getInstance().newCall(request).execute();
            responsemap.put("code",response.code());
            responsemap.put("message",response.message() == null ? "" : response.message());
            responsemap.put("body",response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
            responsemap.put("code", "");
            responsemap.put("message","Response is null");
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
//                            .addInterceptor(new LoggingInterceptor())
                            .certificatePinner(new CertificatePinner
                                    .Builder()
//                                    .add("222222.com", "sha256/3333333333333333=")//证书
                                    .build())
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
            Request request = chain.request();
            long t1 = System.nanoTime();//请求发起的时间
            LogUtil.i("Post请求：",request.url()+"");
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
