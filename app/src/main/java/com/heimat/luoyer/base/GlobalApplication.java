package com.heimat.luoyer.base;

import android.app.Application;
import android.content.Context;
import android.support.compat.BuildConfig;

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
    }
    public static Context getContext() {
        return mContext;
    }

}
