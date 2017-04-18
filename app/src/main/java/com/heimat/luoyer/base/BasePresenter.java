package com.heimat.luoyer.base;

import com.heimat.luoyer.http.retrofit.ApiService;
import com.heimat.luoyer.http.retrofit.RetrofitHelper;

/**
 * Created by code5 on 2017/3/28.
 */
public class BasePresenter<V extends BaseView> implements Presenter<V> {
    protected V mvpView;
    public ApiService mApiService=null;
    public BasePresenter(V mvpView){
        if(mApiService==null){
            mApiService = RetrofitHelper.getApiService();
        }
        attachView(mvpView);
    }
    @Override
    public void attachView(V view) {
        mvpView=view;
    }

    @Override
    public void detachView() {
        mvpView=null;
    }
}
