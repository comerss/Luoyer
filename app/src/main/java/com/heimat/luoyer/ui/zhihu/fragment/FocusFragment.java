package com.heimat.luoyer.ui.zhihu.fragment;

import com.heimat.luoyer.R;
import com.heimat.luoyer.base.BaseMvpFragment;
import com.heimat.luoyer.ui.zhihu.HuInterfaces;
import com.heimat.luoyer.ui.zhihu.bean.News;
import com.heimat.luoyer.ui.zhihu.bean.ResultResponse;

import java.util.List;


/**
 * Created by code5 on 2017/4/10.
 */
public class FocusFragment extends BaseMvpFragment<ListPresenter> implements HuInterfaces.HuListView {
    @Override
    public ListPresenter newPresenter() {
        return new ListPresenter(getActivity(),this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_focus;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }


    @Override
    public void showList(ResultResponse<List<News>> newsSummary) {

    }
}
