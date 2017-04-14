package com.heimat.luoyer.http;

import com.heimat.luoyer.base.BaseBean;
import com.heimat.luoyer.interfaces.HttpCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by code5 on 2017/4/14.
 */
public class Request<T extends BaseBean> {
    private int requestType;//0  get   1 post
    private String URL;
    private Map<String, Object> hashMap;
    private Class<T> clazz;

    public Request(int requestType, String url, Class<T> clazz) {
        this.requestType = requestType;
        URL = url;
        this.clazz=clazz;
    } public Request(int requestType, String url, Class<T> clazz,Map<String,Object> hashMap) {
        this.requestType = requestType;
        URL = url;
        this.clazz=clazz;
        this.hashMap=hashMap;
    }
    public void execute(HttpCallBack<T> httpCallBack){
        performRequest(requestType,httpCallBack);
    }

    private void performRequest(int requestType, HttpCallBack<T> httpCallBack) {
        if(requestType==0){
            performGetRequest(httpCallBack);
        }else if(requestType==1){
            performPostRequest(httpCallBack);
        }
    }
    public Request<T> addArgument(HashMap<String,Object> argumentmap) {
        hashMap = argumentmap;
        return this;
    }

    private void performPostRequest(final HttpCallBack<T> httpCallBack) {
        OkHttpUtils.doGet(URL, clazz, new HttpCallBack<T>() {
            @Override
            public void onSuccess(int requestCode, String json, T t) {
                    httpCallBack.onSuccess(requestCode,json,t);
            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                httpCallBack.onError(errorCode,errorMessage);
            }
        });
    }

    private void performGetRequest(final HttpCallBack<T> httpCallBack) {
        OkHttpUtils.doPost(URL,hashMap, clazz, new HttpCallBack<T>() {
            @Override
            public void onSuccess(int requestCode, String json, T t) {
                httpCallBack.onSuccess(requestCode,json,t);
            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                httpCallBack.onError(errorCode,errorMessage);
            }
        });
    }
}
