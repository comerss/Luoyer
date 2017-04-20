package com.heimat.albumselectlibrary.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

/**
 * Created by code5 on 2017/4/20.
 */
public class StatusColorUtils {
    public static void setStatusBarColor(@NonNull Activity activity, @ColorInt int statusColor) {
        if(Build.VERSION.SDK_INT >= 21) {
            setStatusBarColor_1(activity, statusColor);
        } else if(Build.VERSION.SDK_INT >= 19) {
            setStatusBarColor_2(activity, statusColor);
        }

    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    static void setStatusBarColor_1(Activity activity, int statusColor) {
        Window window = activity.getWindow();
        window.clearFlags(67108864);
        window.addFlags(-2147483648);
        window.setStatusBarColor(statusColor);
        window.getDecorView().setSystemUiVisibility(0);
        ViewGroup mContentView = (ViewGroup)window.findViewById(16908290);
        View mChildView = mContentView.getChildAt(0);
        if(mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false);
            ViewCompat.requestApplyInsets(mChildView);
        }
    }
    @TargetApi(19)
    static void setStatusBarColor_2(Activity activity, int statusColor) {
        Window window = activity.getWindow();
        window.addFlags(67108864);
        ViewGroup mContentView = (ViewGroup)window.findViewById(16908290);
        View mContentChild = mContentView.getChildAt(0);
        int statusBarHeight = getStatusBarHeight(activity);
        removeFakeStatusBarViewIfExist(activity);
        addFakeStatusBarView(activity, statusColor, statusBarHeight);
        addMarginTopToContentChild(mContentChild, statusBarHeight);
        if(mContentChild != null) {
            ViewCompat.setFitsSystemWindows(mContentChild, false);
        }

    }
    private static int getStatusBarHeight(Context context) {
        int result = 0;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(resId > 0) {
            result = context.getResources().getDimensionPixelOffset(resId);
        }

        return result;
    }

    private static View addFakeStatusBarView(Activity activity, int statusBarColor, int statusBarHeight) {
        Window window = activity.getWindow();
        ViewGroup mDecorView = (ViewGroup)window.getDecorView();
        View mStatusBarView = new View(activity);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, statusBarHeight);
        layoutParams.gravity = 48;
        mStatusBarView.setLayoutParams(layoutParams);
        mStatusBarView.setBackgroundColor(statusBarColor);
        mStatusBarView.setTag("statusBarView");
        mDecorView.addView(mStatusBarView);
        return mStatusBarView;
    }

    private static void removeFakeStatusBarViewIfExist(Activity activity) {
        Window window = activity.getWindow();
        ViewGroup mDecorView = (ViewGroup)window.getDecorView();
        View fakeView = mDecorView.findViewWithTag("statusBarView");
        if(fakeView != null) {
            mDecorView.removeView(fakeView);
        }

    }
    private static void addMarginTopToContentChild(View mContentChild, int statusBarHeight) {
        if(mContentChild != null) {
            if(!"marginAdded".equals(mContentChild.getTag())) {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams)mContentChild.getLayoutParams();
                lp.topMargin += statusBarHeight;
                mContentChild.setLayoutParams(lp);
                mContentChild.setTag("marginAdded");
            }

        }
    }
}
