package com.qiezzi.choseviewlibrary.bean;

import com.qiezzi.choseviewlibrary.model.IPickerViewData;

/**
 * Created by PC-JOB on 2015/12/9.
 *
 */
public class IndustryModel implements IPickerViewData {
    private String id;
    private String name;
    private String AttendanceCount;

    public IndustryModel() {
    }

    public IndustryModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttendanceCount() {
        return AttendanceCount;
    }

    public void setAttendanceCount(String attendanceCount) {
        AttendanceCount = attendanceCount;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }
}
