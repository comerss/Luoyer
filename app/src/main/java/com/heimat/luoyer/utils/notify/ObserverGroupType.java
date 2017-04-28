package com.heimat.luoyer.utils.notify;

/**
 * Created by CG on 14-4-1.
 * 一个Activity(或者对话框)的类型就是一个组的类型（一个Activity对应一个KEY）
 * @author ll
 * @version 3.7.0
 */
public enum ObserverGroupType {

    MAIN_DRAWLAYOUT,


    /**
     * 这个是金茄子中面签刮起
     */
    APP_DETIALS_GROUP,


    /**
     * 打卡页面
     */
    PUNCH_CLOCK_GROUP,

    /**
     * 优惠券
     */
    COUPONHOME_GROUP,

    /**
     * 考勤统计
     */
    CHECKWORK_GROUP,

    /**
     * 主页面
     */
    HOME_TAB_GROUP,

    /**
     * pay页面
     */
    PAY_GROUP,

    /**
     * 优惠券页面
     */
    COUPONOFF_GROUP,

    /**
     * 优惠券页面
     */
    COUNEWACTIVE_GROUP,


    /**
     * 登录页面
     */
    LOGIN_GROUP,

    /**
     * 聊天页面
     */
    CHAT_GROUP,

    /**
     * 医嘱页面
     */
    EDITMEDICAL_GROUP;




}
