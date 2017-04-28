package com.heimat.luoyer.utils.notify;

/**
 * Created by CG on 14-1-3.
 * 订阅的主题
 * @author ll
 * @version 3.4.0
 */

public enum IssueKey {

	/**
	 * 关闭ConfirmPayDetailActivity
	 */
	KEY_CLOSE_CONFIRMPAYDETAIL,
	/**
	 * 面签刮起通知，
	 */
	KEY_VIDEOOVER,

	/**
	 * 在打卡页面定位的回调
	 */
	KEY_LOCATION,

	/**
	 * 没有开启定位（禁用）
	 */
	KEY_LOCATION_167,


	/**
	 * 点击重新定位
	 */
	KEY_REFRASH_LOCATION,

	/**
	 * 微博分享
	 */
	KEY_WEIBO_SHRAE,

	/**
	 * qq空间分享
	 */
	KEY_QQ_QONE,

	/**
	 * qq盆友圈分享
	 */
	KEY_QQ_FRIEND,

	/**
	 * 微信分享
	 */
	KEY_WEIXIN,

	/**
	 * 优惠券中列表的刷新
	 */
	KEY_REFRESH,


	/**
	 *  月份更新通知
	 */
	KEY_CALENDAR_REFASH,

	/**
	 * 招聘刷新状态
	 */
	KEY_ZHAOPIN_REFASH_STATU,

	/**
	 * 新增优惠券图片信息
	 */
    KEY_NEW_COUPON_PIC,

	/**
	 * 新增优惠券返回列表定位通知
	 */
	KEY_NEW_COUPON_POSITION,

	/**
	 * 邢增优惠券页面
	 */
    KEY_NEW_ADD_COUPON,

	/**
	 * 供应链面签刮起通知
	 */
	KEY_SUPPLYCHAIN_VIDEOOVER,


	/**
	 * 注册登录关闭注册页面和登录页面
	 */
	KEY_ZHUCE_LOGIN,

	/**
	 * 软键盘弹出通知
	 */
	INPUT_METHOD_OPENED,


	/**
	 * 软键盘隐藏通知
	 */
	INPUT_METHOD_CLOSED,

	/**
	 * 医嘱页面通知
	 */
	KEY_PHYSICIAN_ORDER;

}
