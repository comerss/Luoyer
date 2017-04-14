package com.heimat.luoyer.ui.main;

import com.heimat.luoyer.http.retrofit.RetrofitHelper;
import com.heimat.luoyer.http.retrofit.RxPresenter;
import com.heimat.luoyer.http.retrofit.SubscriberCallBack;
import com.heimat.luoyer.ui.zhihu.HuInterfaces;

/**
 * Created by code5 on 2017/4/14.
 */
public class MainPrenter extends RxPresenter<HuInterfaces.HuMainView> {
    public MainPrenter(HuInterfaces.HuMainView mvpView) {
        super(mvpView);
    }

    public void getData(){
        addSubscription(RetrofitHelper.getApiService().getData(), new SubscriberCallBack() {
            @Override
            protected void onSuccess(Object response) {

            }
        });
    }
}
