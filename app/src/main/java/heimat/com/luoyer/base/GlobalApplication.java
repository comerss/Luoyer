package heimat.com.luoyer.base;

import android.app.Application;
import android.content.Context;

import butterknife.ButterKnife;
import heimat.com.luoyer.BuildConfig;

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
