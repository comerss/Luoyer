package com.heimat.luoyer.ui.main;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heimat.albumselectlibrary.ImageSelectorActivity;
import com.heimat.luoyer.R;
import com.heimat.luoyer.base.BaseActivity;
import com.heimat.luoyer.ui.zhihu.HuActivity;

import java.util.ArrayList;



public class MainActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    int[] images = new int[]{R.drawable.a6k, R.drawable.a4r,
            R.drawable.af8, R.drawable.a8_,
            R.drawable.a8a, R.drawable.a8c};
    private MainAdapter mAdapter;
    ArrayList imagesList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        imagesList = new ArrayList();
        for (int i = 0; i < images.length; i++) {
            imagesList.add(images[i]);
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new MainAdapter(this, imagesList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        toActivity(HuActivity.class);
                        break;
                    case 1:
                        toActivity(TopLineActivity.class);
                        break;
                    case 2:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
                                toActivity(ImageSelectorActivity.class);
                            } else {
                               requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},104);
                            }
                        } else {
                            toActivity(ImageSelectorActivity.class);
                        }
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

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(permissions.length>0){
            if(permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE)){
                toActivity(ImageSelectorActivity.class);
            }
        }
    }
}
