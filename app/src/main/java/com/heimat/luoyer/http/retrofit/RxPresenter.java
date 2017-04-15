package com.heimat.luoyer.http.retrofit;

import android.content.Context;

import com.heimat.luoyer.base.BasePresenter;
import com.heimat.luoyer.base.BaseView;
import com.heimat.luoyer.base.RxBus;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by code5 on 2017/4/10.
 */
public class RxPresenter<V extends BaseView> extends BasePresenter {
    protected V mvpView;
    CompositeSubscription mCompositeSubscription;
    public Context mContext;
    public RxPresenter(BaseView mvpView, Context context) {
        super(mvpView);
        mContext=context;
    }

    @Override
    public void detachView() {
        onUnsubscribe();
        mvpView = null;
    }

    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    /**
     * 发送消息
     */
    public <T> void post(T msg) {
        RxBus.getDefault().post(msg);
    }


    public <T> void addSubscription(Observable<T> observable, Subscriber<T> subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
    public  void add(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

}
