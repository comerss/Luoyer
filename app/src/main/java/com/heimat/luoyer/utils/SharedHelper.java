package com.heimat.luoyer.utils;

import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by code5 on 2017/4/14.
 */
public class SharedHelper {
    /**
     * 保存到SharedPreferences的变量名
     *
     */
    public static final String PREFS_NAME = "clinic";

    /**
     * 获取应用环境参数
     * @param name 参数名
     * @return
     */
    public static String getSetting(String name) {
        if (UIUtils.getContext()!=null){
            SharedPreferences setting = UIUtils.getContext().getSharedPreferences(SharedHelper.PREFS_NAME, 0);
            return setting.getString(name, "");
        }
        return "";

    }
    public static String getSetting2( String name) {
        SharedPreferences setting = UIUtils.getContext().getSharedPreferences(
                SharedHelper.PREFS_NAME, 0);
        return setting.getString(name, "0");
    }

    public static int getSettingInt(String name) {
        SharedPreferences setting = UIUtils.getContext().getSharedPreferences(
                SharedHelper.PREFS_NAME, 0);
        return setting.getInt(name, 0);
    }

    public static int getSettingInt(String name,int defaultSize) {
        SharedPreferences setting = UIUtils.getContext().getSharedPreferences(
                SharedHelper.PREFS_NAME, 0);
        return setting.getInt(name, defaultSize);
    }

    public static float getSettingFloat(String name) {
        SharedPreferences setting = UIUtils.getContext().getSharedPreferences(
                SharedHelper.PREFS_NAME, 0);
        return setting.getFloat(name, 0.0f);
    }

    public static long getSettingLong(String name) {
        SharedPreferences setting = UIUtils.getContext().getSharedPreferences(
                SharedHelper.PREFS_NAME, 0);
        return setting.getLong(name, 0);
    }

    public static boolean getSettingBoolean(String name) {
        SharedPreferences setting = UIUtils.getContext().getSharedPreferences(
                SharedHelper.PREFS_NAME, 0);
        return setting.getBoolean(name, false);
    }

    /**
     * 设置应用程序环境参数
     *
     * @param name
     *            参数名
     * @param value
     *            值
     */
    public static void setSetting(String name, String value) {
        SharedPreferences setting = UIUtils.getContext().getSharedPreferences(
                SharedHelper.PREFS_NAME, 0);
        SharedPreferences.Editor editor = setting.edit();
        editor.putString(name, value);
        editor.commit();
    }

    public static void setSetting(String name, int value) {
        SharedPreferences setting = UIUtils.getContext().getSharedPreferences(
                SharedHelper.PREFS_NAME, 0);
        SharedPreferences.Editor editor = setting.edit();
        editor.putInt(name, value);
        editor.commit();
    }

    public static void setSetting(String name, float value) {
        SharedPreferences setting = UIUtils.getContext().getSharedPreferences(
                SharedHelper.PREFS_NAME, 0);
        SharedPreferences.Editor editor = setting.edit();
        editor.putFloat(name, value);
        editor.commit();
    }

    public static void setSetting(String name, long value) {
        SharedPreferences setting = UIUtils.getContext().getSharedPreferences(
                SharedHelper.PREFS_NAME, 0);
        SharedPreferences.Editor editor = setting.edit();
        editor.putLong(name, value);
        editor.commit();
    }

    public static void setSetting(String name, boolean value) {
        SharedPreferences setting = UIUtils.getContext().getSharedPreferences(
                SharedHelper.PREFS_NAME, 0);
        SharedPreferences.Editor editor = setting.edit();
        editor.putBoolean(name, value);
        editor.commit();
    }
    public static void setSetting(String name, ArrayList<?extends Object> value) {
        SharedPreferences setting = UIUtils.getContext().getSharedPreferences(
                SharedHelper.PREFS_NAME, 0);
        SharedPreferences.Editor editor = setting.edit();
        editor.putString(name,value.toString());
        editor.commit();
    }

}
