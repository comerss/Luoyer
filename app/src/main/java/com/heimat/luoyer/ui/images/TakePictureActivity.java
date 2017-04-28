package com.heimat.luoyer.ui.images;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.heimat.albumselectlibrary.ImageSelectorActivity;
import com.heimat.luoyer.R;
import com.heimat.luoyer.base.BaseActivity;
import com.heimat.luoyer.base.RxBus;
import com.heimat.luoyer.http.Constant;
import com.heimat.luoyer.utils.ImageUtils;
import com.heimat.luoyer.utils.LogUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by code5 on 2017/4/20.
 */
public class TakePictureActivity extends BaseActivity {
    private TextView tvBack;
    private TextView txSave;
    private RecyclerView mRecyclerView;
    private PictureAdapter mAdapter;
    ArrayList<ImageManager> images = new ArrayList<>();
    ArrayList<ImageManager> upImages = new ArrayList<>();
    ArrayList<ImageManager> selectImages = new ArrayList<>();
    ArrayList<Integer> imageTag = new ArrayList<>();
    String[] descp = new String[]{"吃","喝","点击","就欧迪芬","后if哈德","欧迪芬","OnReuslt","HelloWorld"};
    private Subscription mSubscription;
    private int fromType = 0;

    @Override
    public void initBefore() {
        if (getIntent().getExtras() != null) {
            upImages = (ArrayList<ImageManager>) getIntent().getExtras().getSerializable("Image");
            fromType = getIntent().getExtras().getInt("fromType");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_take_picture;
    }

    @Override
    public void initView() {
        tvBack = (TextView) findViewById(R.id.tvBack);
        txSave = (TextView) findViewById(R.id.txSave);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new PictureAdapter(this, new ArrayList());
        mRecyclerView.setAdapter(mAdapter);
        if (fromType == 2) {
            txSave.setText("");
        }
    }

    @Override
    public void initData() {
        if (upImages == null || upImages.size() == 0) {
            ImageManager imageManager;
            for (int i = 0; i < descp.length; i++) {
                if (descp[i].equals("就欧迪芬") ||
                        descp[i].equals("点击") || descp[i].equals("HelloWorld") ||
                        descp[i].equals("其他")) {
                    imageManager = new ImageManager(i, descp[i], false);
                } else {
                    imageManager = new ImageManager(i, descp[i], true);
                }
                upImages.add(imageManager);
            }
            mAdapter.setNewData(upImages);
        } else {
            Collections.sort(upImages);
            mAdapter.setNewData(upImages);
        }
    }

    @Override
    public void initListener() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("ImageManager", mAdapter.getItem(position));
                bundle.putInt(ImageSelectorActivity.EXTRA_MAX_SELECT_NUM, 1);
                toActivityForResult(SelectPictureActivity.class, bundle, 55);
            }
        });
        mSubscription = RxBus.getDefault().take(ImageManager.class).subscribe(new Action1<ImageManager>() {
            @Override
            public void call(ImageManager imageManager) {
                String path = Constant.FILEROOT + System.currentTimeMillis() + ".png";
                ImageUtils.saveImage(imageManager.LocalPath, path);
                imageManager.LocalPath = path;
                mAdapter.getItem(imageManager.Tag).LocalPath = imageManager.LocalPath;
                LogUtil.i("image",imageManager.LocalPath.toString());
                mAdapter.notifyDataSetChanged();
                if (imageTag.contains(imageManager.Tag)) {
                } else {
                    imageTag.add(imageManager.Tag);
                }
            }
        });
        txSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageTag.size() == 0) {
                    finish();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Images", (Serializable) mAdapter.getData());
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
