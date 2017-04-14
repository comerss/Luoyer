package com.heimat.luoyer.http.retrofit;

import com.heimat.luoyer.base.BaseView;
import com.heimat.luoyer.base.Presenter;
import com.heimat.luoyer.base.RxBus;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by code5 on 2017/4/10.
 */
public class RxPresenter<V extends BaseView> implements Presenter<V> {
    protected V mvpView;
    CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(V view) {
        mvpView = view;
    }

    @Override
    public void detachView() {
        onUnsubscribe();
        mvpView = null;
    }

    public RxPresenter(V mvpView) {
        attachView(mvpView);
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


    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

}
