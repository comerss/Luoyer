package com.heimat.luoyer.ui.zhihu.fragment;

import android.content.Context;

import com.heimat.luoyer.http.retrofit.RxPresenter;
import com.heimat.luoyer.http.retrofit.RxSubscriber;
import com.heimat.luoyer.ui.zhihu.HuInterfaces;
import com.heimat.luoyer.ui.zhihu.bean.News;
import com.heimat.luoyer.ui.zhihu.bean.ResultResponse;
import com.heimat.luoyer.utils.ToastUtils;

import java.util.List;


/**
 * Created by code5 on 2017/4/15.
 */
public class ListPresenter extends RxPresenter<HuInterfaces.HuListView> {
    private Context mContext;
    public ListPresenter(Context context, HuInterfaces.HuListView mvpView) {
        super(mvpView,context);
        mContext=context;
        attachView(mvpView);
    }

    public void getNewsList(String catergray, int PageIndex, boolean b) {
        addSubscription(mApiService.getNews(catergray, PageIndex), new RxSubscriber<ResultResponse<List<News>>>(mContext,b) {
            @Override
            protected void _onNext(ResultResponse<List<News>> listResultResponse) {
                mView.showList(listResultResponse);
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.showToast(message);
            }
        });
        addSubscription(mApiService.getNews("999",99), new RxSubscriber<ResultResponse<List<News>>>(mContext, b) {
            @Override
            protected void _onNext(ResultResponse<List<News>> listResultResponse) {
                mView.showList(listResultResponse);
            }

            @Override
            protected void _onError(String message) {

            }
        });
    }
}
