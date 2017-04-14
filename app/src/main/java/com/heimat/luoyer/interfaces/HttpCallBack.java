package com.heimat.luoyer.interfaces;

/**
 * Created by code5 on 2017/4/10.
 */
public interface HttpCallBack<T> {
    /**
     * 访问网络成功
     */
    void onSuccess(int requestCode,String json, T t);

    /**
     * 有错的类型请求
     */
    void onError(int errorCode, String errorMessage);
}
