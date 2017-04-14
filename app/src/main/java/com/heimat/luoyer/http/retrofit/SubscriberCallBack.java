package com.heimat.luoyer.http.retrofit;

/**
 * Created by Administrator
 * on 2016/5/18.
 */
public abstract class SubscriberCallBack<T> extends BaseCallBack<T> {


    @Override
    public void onNext(T response) {

    }

    protected abstract void onSuccess(T response);
}
