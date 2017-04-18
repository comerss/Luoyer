package com.heimat.luoyer.ui.main;

import android.content.Context;

import com.heimat.luoyer.http.retrofit.RetrofitHelper;
import com.heimat.luoyer.http.retrofit.RxPresenter;
import com.heimat.luoyer.http.retrofit.SubscriberCallBack;
import com.heimat.luoyer.ui.zhihu.HuInterfaces;

/**
 * Created by code5 on 2017/4/14.
 */
public class MainPrenter extends RxPresenter<HuInterfaces.HuMainView> {
    public MainPrenter(HuInterfaces.HuMainView mvpView, Context context) {
        super(mvpView,context);
    }

    public void getData(){
        addSubscription(RetrofitHelper.getApiService().getData(), new SubscriberCallBack() {
            @Override
            protected void onSuccess(Object response) {

            }
        });
    }
}
