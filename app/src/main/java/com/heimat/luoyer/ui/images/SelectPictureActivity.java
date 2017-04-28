package com.heimat.luoyer.ui.images;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.heimat.albumselectlibrary.ImageSelectorActivity;
import com.heimat.luoyer.base.RxBus;

import java.util.ArrayList;

/**
 * Created by code5 on 2017/4/28.
 * 可以根据自己的项目，尤其是多图选择需要自己梳理modle的时候传递数据
 */
public class SelectPictureActivity extends ImageSelectorActivity {
    ImageManager mImageManager=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getExtras()!=null)
            mImageManager= (ImageManager) getIntent().getExtras().getSerializable("ImageManager");
    }

    @Override
    public void doResult(ArrayList<String> images) {
        mImageManager.LocalPath=images.get(0);
        RxBus.getDefault().post(mImageManager);
       /* Intent intent=new Intent();
//        intent.put("List",images);
        Bundle bundle=new Bundle();
        bundle.putSerializable("List",mImageManager);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);*/
        finish();
    }

}
