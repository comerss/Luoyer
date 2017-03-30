package heimat.com.luoyer.ui.zhihu;

import heimat.com.luoyer.R;
import heimat.com.luoyer.base.BaseMvpFragment;

/**
 * Created by code5 on 2017/3/30.
 */
public class HuListFragment extends BaseMvpFragment<HuMainPresenter> implements HuInterfaces.HuMainView{
    @Override
    public HuMainPresenter newPresenter() {
        return new HuMainPresenter(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_hu_list;
    }


    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void showData() {

    }

    @Override
    public void showLoadMore() {

    }
}
