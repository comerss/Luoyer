package com.heimat.luoyer.ui.zhihu;


import com.heimat.luoyer.base.BaseView;
import com.heimat.luoyer.ui.zhihu.bean.NewsSummary;

import java.util.List;

/**
 * Created by code5 on 2017/3/29.
 */
public class HuInterfaces {
    public interface HuView extends BaseView {

    }

    public interface HuPresenters {
        void getData();

        void getLoadMore();
    }

    public interface HuMainView extends BaseView{
        void showData();

        void showLoadMore();
    }
    public interface HuMainPresenters{
        void getData();

        void getLoadMore();
    }
    public interface HuListView extends BaseView{
        void showList(List<NewsSummary> newsSummary);
    }
}
