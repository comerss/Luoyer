package com.heimat.luoyer.ui.zhihu.fragment;

import android.content.Context;

import com.heimat.luoyer.http.retrofit.RxPresenter;
import com.heimat.luoyer.http.retrofit.RxSubscriber;
import com.heimat.luoyer.ui.zhihu.HuInterfaces;
import com.heimat.luoyer.ui.zhihu.bean.NewsSummary;

import java.util.List;


/**
 * Created by code5 on 2017/4/15.
 */
public class ListPresenter extends RxPresenter<HuInterfaces.HuListView> {
    public ListPresenter(Context context, HuInterfaces.HuListView mvpView) {
        super(mvpView,context);
    }

    public void getNewsList( String type, final String id, int startPage) {
        addSubscription(mApiService.getNewsListData(type, id, startPage), new RxSubscriber<List<NewsSummary>>(mContext) {
            @Override
            protected void _onNext(List<NewsSummary> newsSummaries) {
                mvpView.showList(newsSummaries);
            }

            @Override
            protected void _onError(String message) {

            }
        });
    }
}
