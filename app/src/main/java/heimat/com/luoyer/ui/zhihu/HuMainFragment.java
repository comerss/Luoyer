package heimat.com.luoyer.ui.zhihu;

import heimat.com.luoyer.base.BaseMvpFragment;

/**
 * Created by code5 on 2017/3/29.
 */
public class HuMainFragment extends BaseMvpFragment<HuMainPresenter> implements HuInterfaces.HuMainView {
    @Override
    public HuMainPresenter newPresenter() {
        return new HuMainPresenter(this);
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void showData() {

    }

    @Override
    public void showLoadMore() {

    }
}
