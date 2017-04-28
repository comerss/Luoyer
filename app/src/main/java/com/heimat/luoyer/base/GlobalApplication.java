package com.heimat.luoyer.base;

import android.app.Application;
import android.content.Context;
import android.support.compat.BuildConfig;

import com.heimat.luoyer.http.Constant;
import com.heimat.luoyer.utils.FileUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import butterknife.ButterKnife;

/**
 * Created by code5 on 2017/3/29.
 */
public class GlobalApplication extends Application {
    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        ButterKnife.setDebug(BuildConfig.DEBUG);
        initImageLoader();
        FileUtils.createFilePath(Constant.FILEROOT);
    }
    public static Context getContext() {
        return mContext;
    }
    private void initImageLoader() {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(this);
        config.memoryCacheExtraOptions(480, 800);
        config.diskCacheExtraOptions(480, 800, null);
        config.diskCacheSize(100 * 1024 * 1024); // 100 MiB
        if (BuildConfig.DEBUG) {
            config.writeDebugLogs(); // Remove for release app
        }
        ImageLoader.getInstance().init(config.build());
    }
}
