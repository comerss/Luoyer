package com.heimat.luoyer.http.retrofit;

import android.os.Handler;
import android.os.Looper;

import com.heimat.luoyer.utils.ToastUtils;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/2/9 0009.
 */

public abstract class BaseCallBack<T> extends Subscriber<T> {
    private Handler mDelivery;

    public BaseCallBack() {
        mDelivery = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onCompleted() {
        mDelivery = null;
    }

    @Override
    public void onError(final Throwable e) {
        e.printStackTrace();
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (e instanceof SocketTimeoutException) {
                    ToastUtils.showToast("网络连接超时，请检查网络");
                } else if (e instanceof SocketException) {
                    if (e instanceof ConnectException) {
                        ToastUtils.showToast("网络未连接，请检查网络");
                    } else {
                        ToastUtils.showToast("网络错误，请检查网络");
                    }
                }
                onError();
            }
        });
    }




    protected void onError() {
    }

    protected void onFailure(T response) {
    }
}
