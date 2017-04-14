package com.heimat.luoyer.ui.zhihu;


import com.heimat.luoyer.base.BasePresenter;

/**
 * Created by code5 on 2017/3/29.
 */
public class HuPresenter extends BasePresenter<HuInterfaces.HuView> implements HuInterfaces.HuPresenters {
    public HuPresenter(HuInterfaces.HuView mvpView) {
        super(mvpView);
    }

    @Override
    public void getData() {
    }

    @Override
    public void getLoadMore() {

    }
}
