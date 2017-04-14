package com.heimat.luoyer.utils;

import android.widget.Toast;

/**
 * Created by code5 on 2017/4/14.
 */
public class ToastUtils {
    private static Toast mToast;

    /**
     * 显示Toast
     */
    public static void showToast( CharSequence text) {
        if (mToast == null) {
            mToast = Toast.makeText(UIUtils.getContext(), text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
        }
        mToast.show();
    }
}
