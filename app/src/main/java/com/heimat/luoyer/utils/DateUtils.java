package com.heimat.luoyer.utils;

import android.text.format.DateFormat;

import java.util.Date;

/**
 * Created by code5 on 2017/4/19.
 */
public class DateUtils {
    public static final long ONE_MINUTE_MILLIONS = 60 * 1000;
    public static final long ONE_HOUR_MILLIONS = 60 * ONE_MINUTE_MILLIONS;
    public static final long ONE_DAY_MILLIONS = 24 * ONE_HOUR_MILLIONS;
    public static String getShortTime(long millis) {
        String str = "";

        long durTime = System.currentTimeMillis() - millis;

        if (durTime <= 10 * ONE_MINUTE_MILLIONS) {
            str = "刚刚";
        } else if (durTime < ONE_HOUR_MILLIONS) {
            str = durTime / ONE_MINUTE_MILLIONS + "分钟前";
        } else if (durTime < ONE_HOUR_MILLIONS * 24) {
            str = durTime / ONE_HOUR_MILLIONS + "小时前";
        } else {
            Date date = new Date(millis);
            str = DateFormat.format("MM-dd HH:mm", date) + "";
        }
        return str;
    }
}
