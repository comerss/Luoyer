package com.heimat.luoyer.ui.zhihu.fragment;

import android.widget.TextView;

import com.heimat.luoyer.R;
import com.heimat.luoyer.base.BaseMvpFragment;
import com.heimat.luoyer.ui.zhihu.HuInterfaces;
import com.heimat.luoyer.ui.zhihu.bean.NewsSummary;

import java.util.List;

import butterknife.BindView;

/**
 * Created by code5 on 2017/3/30.
 */
public class HuListFragment extends BaseMvpFragment<ListPresenter> implements HuInterfaces.HuListView {

    @BindView(R.id.txContent)
    TextView mTxContent;
    private int mStatus;
    private String mNewsId;
    private String mNewsType;
    private int mStartPage = 0;

    @Override
    public void initBefore() {
        if (getArguments() != null) {
            mStatus = getArguments().getInt("Status");
            mNewsId = getArguments().getString("news_id");
            mNewsType = getArguments().getString("news_type");
        }
    }

    @Override
    public ListPresenter newPresenter() {
        return new ListPresenter(getActivity(), this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_hu_list;
    }


    @Override
    public void initView() {
        mTxContent.setText("我是第---》" + mStatus + "  页");
    }

    @Override
    public void initData() {
//        mPresenter.getNewsList(mNewsType,mNewsId,1);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void showList(List<NewsSummary> newsSummary) {

    }
}
