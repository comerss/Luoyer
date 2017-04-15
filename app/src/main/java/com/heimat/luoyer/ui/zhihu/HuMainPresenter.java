package com.heimat.luoyer.ui.zhihu;


import com.heimat.luoyer.base.BasePresenter;

/**
 * Created by code5 on 2017/3/29.
 * 这里的MVP是不完善的MVP小项目可以这样简写省时间，说白了就是没有M 的MVP Ｍ　的逻辑放在了preenter里面
 * 看起来更像是一个长长的回调，当然自己可以写完整的MVP模式，根据自己的项目大小以及个人写的习惯就好
 * 适合自己的才是最后的，没有必要一定要遵从谁的
 *
 * 一下更懒的方式就是presenter不用写接口，直接在presenter类里面进行写网络请求的方法调用，还是那句话看自己喜欢！！！
 */
public class HuMainPresenter extends BasePresenter<HuInterfaces.HuMainView> implements HuInterfaces.HuMainPresenters {
    public HuMainPresenter(HuInterfaces.HuMainView mvpView) {
        super(mvpView);
    }

    @Override
    public void getData() {
    }

    @Override
    public void getLoadMore() {

    }


}
