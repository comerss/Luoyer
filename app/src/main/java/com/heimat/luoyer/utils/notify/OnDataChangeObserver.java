package com.heimat.luoyer.utils.notify;

/**
 * 数据监听
 *
 * @author ll
 * @version 1.0.0
 */
public interface OnDataChangeObserver {
    /**
     * 数据更新接口
     *
     * @param issue issue
     * @param o     Object
     */
    public void onDataChanged(IssueKey issue, Object o);
}
