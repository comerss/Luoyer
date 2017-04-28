package com.heimat.luoyer.ui.images;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heimat.luoyer.R;

import java.util.List;

/**
 * Created by code5 on 2017/4/20.
 */
public class PictureAdapter extends BaseQuickAdapter<ImageManager> {
    Context mContext;
    public PictureAdapter(Context context, List data) {
        super(context, R.layout.adapter_take_picture, data);
        mContext=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ImageManager item) {
        ImageView img=helper.getView(R.id.mImg);
        helper.setText(R.id.txDescp,item.Descp);
        if(item.isHaveTo){
            helper.setVisible(R.id.isHaveTo,true);
        }else{
            helper.setVisible(R.id.isHaveTo,false);
        }
        if(!TextUtils.isEmpty(item.LocalPath)){
            Glide.with(mContext).load(item.LocalPath)
                    .placeholder(R.drawable.ic_camera)
                    .error(R.drawable.ic_camera)
                    .into(img);
        }else{
            Glide.with(mContext)
                    .load(item.Url)
                    .placeholder(R.drawable.ic_camera)
                    .error(R.drawable.ic_camera)
                    .into(img);
        }
    }
}
