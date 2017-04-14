package com.heimat.luoyer.utils;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heimat.luoyer.base.BaseBean;
import com.heimat.luoyer.base.GlobalApplication;

import java.util.List;


/**
 * Created by code5 on 2017/3/29.
 */
public class UIUtils {
    public static Context getContext(){
        return GlobalApplication.getContext();
    }
    //根据model对象判断adapter是否需要加载更多和刷新等操作
    public static <T extends BaseBean> void autoLoad(RecyclerView recyclerView, BaseQuickAdapter mAdapter, T t, List list){
        if(t!=null&&list!=null){
            if(t.CurrentPage==1){
                mAdapter.setNewData(list);
                if(t.TotalPages>1){
                    mAdapter.notifyDataChangedAfterLoadMore(true);
                }else{
                    mAdapter.notifyDataChangedAfterLoadMore(false);
                }
            }else{
                if(t.TotalPages>t.CurrentPage){
                    mAdapter.notifyDataChangedAfterLoadMore(list,true);
                }else{
                    mAdapter.notifyDataChangedAfterLoadMore(list,false);
                }
            }
        }
    }
    public static <T extends BaseBean> void autoLoad(RecyclerView recyclerView, BaseQuickAdapter mAdapter, T t, List list, SwipeRefreshLayout mRefreshLayout){
        if(t!=null&&list!=null){
            if(t.CurrentPage==1){
                mAdapter.setNewData(list);
                if(t.TotalPages>1){
                    mAdapter.notifyDataChangedAfterLoadMore(true);
                }else{
                    mAdapter.notifyDataChangedAfterLoadMore(false);
                }
            }else{
                if(t.TotalPages>t.CurrentPage){
                    mAdapter.notifyDataChangedAfterLoadMore(list,true);
                }else{
                    mAdapter.notifyDataChangedAfterLoadMore(list,false);
                }
            }
        }
        mRefreshLayout.setRefreshing(false);
    }
}
