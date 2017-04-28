package com.heimat.luoyer.utils.notify;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by CG on 14-4-1.
 * 一个组对象对应一个Activity对象
 * @author ll
 * @version 3.7.0
 */
public final class ObserverGroup {

    private ObserverGroupType mGroupType;

    private ObserverGroup(ObserverGroupType type) {
        mGroupType = type;
    }

    private ObserverGroupType getType() {
        return mGroupType;
    }

    // 检查使用
    /** 设置某一组类型的对象的最大数量 */
    private static Map<ObserverGroupType, Integer> mGroupDefMaxCount = new HashMap<ObserverGroupType, Integer>();
    /** 设置某一组类型的对象的set */
    private static Map<ObserverGroupType, Set<ObserverGroup>> mGroupMap = new HashMap<ObserverGroupType, Set<ObserverGroup>>();

    static void setGroupDefMaxCount(ObserverGroupType type, int count) {
        mGroupDefMaxCount.put(type, count);
    }

    private static void addGroup(ObserverGroup group) {
        int maxCount = 2;
        if (mGroupDefMaxCount.get(group.getType()) != null) {
            maxCount = mGroupDefMaxCount.get(group.getType());
        }

        Set<ObserverGroup> observerGroups = mGroupMap.get(group.getType());
        if (observerGroups == null) {
            observerGroups = new HashSet<ObserverGroup>(2);
            mGroupMap.put(group.getType(), observerGroups);
        }
        if (observerGroups.size() < maxCount) {
            observerGroups.add(group);
        } else {
            try {
				throw new IllegalArgumentException("repeat register, group = " + group.getType());
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }

    static void removeGroup(ObserverGroup group) {
        Set<ObserverGroup> observerGroups = mGroupMap.get(group.getType());
        if (observerGroups != null) {
            observerGroups.remove(group);
        }
    }

    private static ObserverGroup mConfirmPayDetailGroup;
    private static ObserverGroup mApplyDetailsGroup;
    private static ObserverGroup mClockGroup;
    private static ObserverGroup mCouponHomeGroup;
    private static ObserverGroup mCheckWorkGroup;
    private static ObserverGroup mHomeTabGroup;
    private static ObserverGroup mCouponOffGroup;
    private static ObserverGroup mCouNewGroup;
    private static ObserverGroup mLoginGroup;
    private static ObserverGroup mPayGroup;
    private static ObserverGroup mChatGroup;
    private static ObserverGroup mEditMedicalGroup;



    /**
     * createDrawLayoutGroup
     * @return ObserverGroup
     */
    public static ObserverGroup createConfirmPayDetailGroup() {
        mConfirmPayDetailGroup = new ObserverGroup(ObserverGroupType.MAIN_DRAWLAYOUT);
        addGroup(mConfirmPayDetailGroup);
        return mConfirmPayDetailGroup;
    }

    /**
     * getDrawLayoutGroup
     * @return ObserverGroup
     */
    public static ObserverGroup getConfirmPayDetailGroup() {
        return mConfirmPayDetailGroup;
    }



    /**
     * createApplyDetialsGroup
     * @return ObserverGroup
     */
    public static ObserverGroup createApplyDetialsGroup() {
        mApplyDetailsGroup = new ObserverGroup(ObserverGroupType.APP_DETIALS_GROUP);
        addGroup(mApplyDetailsGroup);
        return mApplyDetailsGroup;
    }

    /**
     * getApplyDetialsGroup
     * @return ObserverGroup
     */
    public static ObserverGroup getApplyDetialsGroup() {
        return mApplyDetailsGroup;
    }


    /**
     * createApplyDetialsGroup
     * @return ObserverGroup
     */
    public static ObserverGroup createClockGroup() {
        mClockGroup = new ObserverGroup(ObserverGroupType.PUNCH_CLOCK_GROUP);
        addGroup(mClockGroup);
        return mClockGroup;
    }

    /**
     * getApplyDetialsGroup
     * @return ObserverGroup
     */
    public static ObserverGroup getClockGroup() {
        return mClockGroup;
    }


    /**
     * createApplyDetialsGroup
     * @return ObserverGroup
     */
    public static ObserverGroup createCouponHomeGroup() {
        mCouponHomeGroup = new ObserverGroup(ObserverGroupType.COUPONHOME_GROUP);
        addGroup(mCouponHomeGroup);
        return mCouponHomeGroup;
    }

    /**
     * getApplyDetialsGroup
     * @return ObserverGroup
     */
    public static ObserverGroup getCouponHomeGroup() {
        return mCouponHomeGroup;
    }


    /**
     * createApplyDetialsGroup
     * @return ObserverGroup
     */
    public static ObserverGroup createCheckWorkGroup() {
        mCheckWorkGroup = new ObserverGroup(ObserverGroupType.CHECKWORK_GROUP);
        addGroup(mCheckWorkGroup);
        return mCheckWorkGroup;
    }

    /**
     * getApplyDetialsGroup
     * @return ObserverGroup
     */
    public static ObserverGroup getCheckWorkGroup() {
        return mCheckWorkGroup;
    }



    /**
     * createHomeTabGroup
     * @return ObserverGroup
     */
    public static ObserverGroup createHomeTabGroup() {
        mHomeTabGroup = new ObserverGroup(ObserverGroupType.HOME_TAB_GROUP);
        addGroup(mHomeTabGroup);
        return mHomeTabGroup;
    }

    /**
     * getHomeTabGroup
     * @return ObserverGroup
     */
    public static ObserverGroup getHomeTabGroup() {
        return mHomeTabGroup;
    }


    /**
     * createPayGroup
     * @return ObserverGroup
     */
    public static ObserverGroup createPayGroup() {
        mPayGroup = new ObserverGroup(ObserverGroupType.PAY_GROUP);
        addGroup(mPayGroup);
        return mPayGroup;
    }

    /**
     * getPayGroup
     * @return ObserverGroup
     */
    public static ObserverGroup getPayGroup() {
        return mPayGroup;
    }

    /**
     * createHomeTabGroup
     * @return ObserverGroup
     */
    public static ObserverGroup createCouponOffGroup() {
        mCouponOffGroup = new ObserverGroup(ObserverGroupType.COUPONOFF_GROUP);
        addGroup(mCouponOffGroup);
        return mCouponOffGroup;
    }

    /**
     * getHomeTabGroup
     * @return ObserverGroup
     */
    public static ObserverGroup getCouponOffGroup() {
        return mCouponOffGroup;
    }


    /**
     * createHomeTabGroup
     * @return ObserverGroup
     */
    public static ObserverGroup createCouNewActivityGroup() {
        mCouNewGroup = new ObserverGroup(ObserverGroupType.COUNEWACTIVE_GROUP);
        addGroup(mCouNewGroup);
        return mCouNewGroup;
    }

    /**
     * getHomeTabGroup
     * @return ObserverGroup
     */
    public static ObserverGroup getCouNewActivityGroup() {
        return mCouNewGroup;
    }


    /**
     * createHomeTabGroup
     * @return ObserverGroup
     */
    public static ObserverGroup createLoginActivityGroup() {
        mLoginGroup = new ObserverGroup(ObserverGroupType.LOGIN_GROUP);
        addGroup(mLoginGroup);
        return mLoginGroup;
    }

    /**
     * getHomeTabGroup
     * @return ObserverGroup
     */
    public static ObserverGroup getLoginActivityGroup() {
        return mLoginGroup;
    }


    /**
     * createChatActivityGroup
     * @return ObserverGroup
     */
    public static ObserverGroup createChatActivityGroup() {
        mChatGroup = new ObserverGroup(ObserverGroupType.CHAT_GROUP);
        addGroup(mChatGroup);
        return mChatGroup;
    }

    /**
     * getChatActivityGroup
     * @return ObserverGroup
     */
    public static ObserverGroup getChatActivityGroup() {
        return mChatGroup;
    }


    /**
     * createEditMedicalActivityGroup
     * @return ObserverGroup
     */
    public static ObserverGroup createEditMedicalActivityGroup() {
        mEditMedicalGroup = new ObserverGroup(ObserverGroupType.EDITMEDICAL_GROUP);
        addGroup(mEditMedicalGroup);
        return mEditMedicalGroup;
    }

    /**
     * getEditMedicalActivityGroup
     * @return ObserverGroup
     */
    public static ObserverGroup getEditMedicalActivityGroup() {
        return mEditMedicalGroup;
    }

}
