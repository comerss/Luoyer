package com.heimat.luoyer.ui.zhihu.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.heimat.luoyer.R;
import com.heimat.luoyer.ui.zhihu.bean.News;
import com.heimat.luoyer.utils.ConstanceValue;
import com.heimat.luoyer.utils.DateUtils;
import com.heimat.luoyer.utils.ImageLoaderUtils;

import java.util.List;

/**
 * Created by code5 on 2017/4/15.
 */
public class NewsListAdapter extends BaseQuickAdapter<News> {
    public NewsListAdapter(List<News> data) {
        super(R.layout.item_news, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, News news) {
        //防止复用View没有改变主题，重新设置
        if (news.article_genre.equals(ConstanceValue.ARTICLE_GENRE_ARTICLE)) {
            //文章类型
            if (news.image_list == null || news.image_list.size() == 0) {
                if (!TextUtils.isEmpty(news.image_url)) {

                    //单图片文章
                    ImageLoaderUtils.displayImage(news.image_url, (ImageView) baseViewHolder.getView(R.id.ivRightImg1));
                    baseViewHolder.setVisible(R.id.rlRightImg, true)
                            .setVisible(R.id.viewFill, true);
                }
            } else {
                //3张图片
                baseViewHolder.setVisible(R.id.llCenterImg, true);
                try {
                    ImageLoaderUtils.displayImage(news.image_list.get(0).url, (ImageView) baseViewHolder.getView(R.id.ivCenterImg1));
                    ImageLoaderUtils.displayImage(news.image_list.get(1).url, (ImageView) baseViewHolder.getView(R.id.ivCenterImg2));
                    ImageLoaderUtils.displayImage(news.image_list.get(2).url, (ImageView) baseViewHolder.getView(R.id.ivCenterImg3));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } else if (news.article_genre.equals(ConstanceValue.ARTICLE_GENRE_GALLERY)) {
            //画廊类型
            if (news.image_list == null) {
                ImageLoaderUtils.displayImage(news.image_url, (ImageView) baseViewHolder.getView(R.id.ivRightImg1));
                baseViewHolder.setVisible(R.id.rlRightImg, true)
                        .setVisible(R.id.viewFill, true);
            } else {
                ImageLoaderUtils.displayImage(news.image_list.get(0).url, (ImageView) baseViewHolder.getView(R.id.ivBigImg));
                baseViewHolder.setVisible(R.id.rlBigImg, true)
                        .setText(R.id.tvImgCount, news.image_list.size() + "图");
            }


        } else if (news.article_genre.equals(ConstanceValue.ARTICLE_GENRE_VIDEO)) {
            //视频类型
            ImageLoaderUtils.displayImage(news.image_url, (ImageView) baseViewHolder.getView(R.id.ivRightImg1));
            baseViewHolder.setVisible(R.id.rlRightImg, true)
                    .setVisible(R.id.viewFill, true)
                    .setVisible(R.id.llVideo, true).setText(R.id.tvDuration, news.video_duration_str);
        }
        baseViewHolder.setText(R.id.tvTitle, news.title)
                .setText(R.id.tvAuthorName, news.source)
                .setText(R.id.tvCommentCount, news.comments_count + "评论")
                .setText(R.id.tvTime, DateUtils.getShortTime(news.behot_time * 1000));
    }
}
