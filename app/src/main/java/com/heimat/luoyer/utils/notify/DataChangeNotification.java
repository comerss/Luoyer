package com.heimat.luoyer.utils.notify;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 数据变更通知接口
 *
 * @author ll
 * @version 1.0.0
 */
public final class DataChangeNotification {

    private static DataChangeNotification mNotification = new DataChangeNotification();

    private final class DataChangeObserver {

        private OnDataChangeObserver mObserver;
        private ObserverGroup mGroup;

        private DataChangeObserver(OnDataChangeObserver observer, ObserverGroup group) {
            mObserver = observer;
            mGroup = group;
        }
    }

    private Map<IssueKey, Set<DataChangeObserver>> mIssueDataChangeObserverMap;

    // 检查使用
    /** 设置某一观察者类型的对象的最大数量 */
    private Map<String, Integer> mObserverDefMaxCount;
    /** 设置某一观察者类型的对象的set */
    private Map<String, Set<OnDataChangeObserver>> mObservers;

    /**
     * getInstance
     *
     * @return DataChangeNotification
     */
    public static DataChangeNotification getInstance() {
        return mNotification;
    }

    private DataChangeNotification() {
        mIssueDataChangeObserverMap = new HashMap<IssueKey, Set<DataChangeObserver>>();
        mObserverDefMaxCount = new HashMap<String, Integer>();
        mObservers = new HashMap<String, Set<OnDataChangeObserver>>();
    }

    /**
     * 添加 - 有检查，同一类型的观察者的对象超过预设值，超出抛异常
     *
     * @param issue    issue
     * @param observer observer
     */
    public void addObserver(IssueKey issue, OnDataChangeObserver observer) {
        addObserver(issue, observer, null);
        checkObserver(observer);
    }

    private void checkObserver(OnDataChangeObserver observer) {
        String type = observer.getClass().getName();
        int maxCount = 2;
        if (mObserverDefMaxCount.get(type) != null) {
            maxCount = mObserverDefMaxCount.get(type);
        }
        Set<OnDataChangeObserver> observers = mObservers.get(type);
        if (observers == null) {
            observers = new HashSet<OnDataChangeObserver>();
            mObservers.put(type, observers);
        }
        observers.add(observer);
        if (observers.size() > maxCount) {
            try {
				throw new IllegalArgumentException("repeat register, observer = " + type);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    /**
     * 添加 - 有检查，同一类型的组的对象最多有两个，超出抛异常
     *
     * @param issue    issue
     * @param observer observer
     * @param group    group
     */
    public void addObserver(IssueKey issue, OnDataChangeObserver observer, ObserverGroup group) {
        Set<DataChangeObserver> dataChangeObserverSet = mIssueDataChangeObserverMap.get(issue);
        if (dataChangeObserverSet == null) {
            dataChangeObserverSet = new HashSet<DataChangeObserver>();
            mIssueDataChangeObserverMap.put(issue, dataChangeObserverSet);
        }

        boolean bFound = false;
        for (DataChangeObserver dataChangeObserver : dataChangeObserverSet) {
            if (dataChangeObserver.mObserver == observer) {
                bFound = true;
                break;
            }
        }

        if (!bFound) {
            dataChangeObserverSet.add(new DataChangeObserver(observer, group));
        }
    }

    /**
     * 删除
     *
     * @param observer 观察者
     */
    public void removeObserver(OnDataChangeObserver observer) {
        for (Set<DataChangeObserver> dataChangeObserverSet : mIssueDataChangeObserverMap.values()) {
            Iterator<DataChangeObserver> iterator = dataChangeObserverSet.iterator();
            while (iterator.hasNext()) {
                DataChangeObserver dataChangeObserver = iterator.next();
                if (observer == dataChangeObserver.mObserver) {
                    iterator.remove();
                }
            }
        }

        String type = observer.getClass().getName();
        Set<OnDataChangeObserver> observers = mObservers.get(type);
        if (observers != null) {
            observers.remove(observer);
        }
    }

    /**
     * 删除
     *
     * @param group group
     */
    public void removeObserver(ObserverGroup group) {
        for (Set<DataChangeObserver> dataChangeObserverSet : mIssueDataChangeObserverMap.values()) {
            Iterator<DataChangeObserver> iterator = dataChangeObserverSet.iterator();
            while (iterator.hasNext()) {
                DataChangeObserver dataChangeObserver = iterator.next();
                if (group == dataChangeObserver.mGroup) {
                    iterator.remove();
                }
            }
        }
        ObserverGroup.removeGroup(group);
    }

    /**
     * 通知
     *
     * @param issue issue
     */
    public void notifyDataChanged(IssueKey issue) {
        notifyDataChanged(issue, null);
    }

    /**
     * 通知
     * @param issue issue
     * @param o     o
     */
    public synchronized void notifyDataChanged(IssueKey issue, Object o) {
        Set<DataChangeObserver> dataChangeObserverSet = mIssueDataChangeObserverMap.get(issue);
        if (dataChangeObserverSet != null) {
            for (DataChangeObserver dataChangeObserver : dataChangeObserverSet) {
                dataChangeObserver.mObserver.onDataChanged(issue, o);
            }
        }
    }
}
