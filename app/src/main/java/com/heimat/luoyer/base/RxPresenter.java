package com.heimat.luoyer.base;

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
        mvpView=view;
    }

    @Override
    public void detachView() {
        mvpView=null;
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
    public void post(Object msg) {
        RxBus.getDefault().post(msg);
    }


    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

}
