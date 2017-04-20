package com.heimat.luoyer.ui.zhihu.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heimat.luoyer.R;
import com.heimat.luoyer.base.BaseMvpFragment;
import com.heimat.luoyer.ui.zhihu.HuInterfaces;
import com.heimat.luoyer.ui.zhihu.adapter.NewsListAdapter;
import com.heimat.luoyer.ui.zhihu.bean.News;
import com.heimat.luoyer.ui.zhihu.bean.ResultResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by code5 on 2017/3/30.
 */
public class HuListFragment extends BaseMvpFragment<ListPresenter> implements HuInterfaces.HuListView {

    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefresh;
    Unbinder unbinder;
    private String[] titles = new String[]{"推荐", "视频", "热点", "社会", "娱乐", "科技", "汽车", "体育", "财经", "军事", "国际", "时尚", "游戏", "旅游", "历史", "探索", "美食", "育儿", "养生", "故事", "美文"};
    private String[] titlesCode = new String[]{"__all__", "video", "news_hot", "news_society", "news_entertainment", "news_tech", "news_car", "news_sports", "news_finance", "news_military", "news_world", "news_fashion", "news_game", "news_travel", "news_history", "news_discovery", "news_food", "news_baby", "news_regimen", "news_story", "news_essay"};
    private int pageIndex = 1;
    private int mPosition;
    private NewsListAdapter mAdapter;

    @Override
    public void initBefore() {
        if (getArguments() != null) {
            mPosition = getArguments().getInt("Position");
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
        mAdapter = new NewsListAdapter(new ArrayList<News>());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        mPresenter.getNewsList(titlesCode[mPosition], 1,true);
        mAdapter.openLoadMore(20,true);
    }

    @Override
    public void initListener() {
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.getNewsList(titlesCode[mPosition], pageIndex,true);
            }
        });
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageIndex=1;
                mPresenter.getNewsList(titlesCode[mPosition], 1, false);
            }
        });
    }

    @Override
    public void showList(ResultResponse<List<News>> newsSummary) {
        if(pageIndex==1){
            mAdapter.setNewData(newsSummary.data);
            if(newsSummary.has_more){
                mAdapter.notifyDataChangedAfterLoadMore(true);
            }else{
                mAdapter.notifyDataChangedAfterLoadMore(false);
            }
            pageIndex=2;
        }else{
            if(newsSummary.has_more){
                mAdapter.notifyDataChangedAfterLoadMore(newsSummary.data,true);
            }else{
                mAdapter.notifyDataChangedAfterLoadMore(newsSummary.data,false);
            }
            pageIndex++;
        }
        mSwipeRefresh.setRefreshing(false);
    }

}
