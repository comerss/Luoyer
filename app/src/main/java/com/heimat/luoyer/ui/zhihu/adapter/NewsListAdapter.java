package com.heimat.luoyer.ui.zhihu.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heimat.luoyer.ui.zhihu.bean.NewsSummary;

import java.util.List;

/**
 * Created by code5 on 2017/4/15.
 */
public class NewsListAdapter extends BaseQuickAdapter<NewsSummary> {
    public NewsListAdapter(Context context, List<NewsSummary> data) {
        super(context, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsSummary item) {

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
