package com.heimat.luoyer.ui.main;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heimat.luoyer.R;
import com.heimat.luoyer.base.BaseActivity;
import com.heimat.luoyer.http.okhttp.HttpManager;
import com.heimat.luoyer.interfaces.HttpCallBack;
import com.heimat.luoyer.ui.zhihu.HuActivity;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    int[] images=new int[]{R.drawable.a6k,R.drawable.a4r,
            R.drawable.af8,R.drawable.a8_,
            R.drawable.a8a,R.drawable.a8c};
    private MainAdapter mAdapter;
    ArrayList imagesList;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        imagesList=new ArrayList();
        for(int i=0;i<images.length;i++){
            imagesList.add(images[i]);
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mAdapter = new MainAdapter(this, imagesList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0:
                        toActivity(HuActivity.class);
                        break;
                    case 1:
                        toActivity(TopLineActivity.class);
                        break;
                    case 2:
//                        toActivity(WXinActivity.class);
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
        //只需要传参数和回调，内部逻辑交给其他实现！
        HttpManager.getData(new HashMap<String, Object>()).execute(new HttpCallBack<NetData>() {
            @Override
            public void onSuccess(int requestCode, String json, NetData netData) {

            }

            @Override
            public void onError(int errorCode, String errorMessage) {

            }
        });
    }

}
