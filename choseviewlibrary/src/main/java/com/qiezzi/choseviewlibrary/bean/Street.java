package com.qiezzi.choseviewlibrary.bean;

import com.qiezzi.choseviewlibrary.model.IPickerViewData;

/**
 * Created by code5 on 2017/1/17.
 */
public class Street implements IPickerViewData {
    public String S;
    public String SC;

    public String getS() {
        return S;
    }

    @Override
    public String toString() {
        return "Street{" +
                "S='" + S + '\'' +
                ", SC='" + SC + '\'' +
                '}';
    }

    public void setS(String s) {
        S = s;
    }

    @Override
    public String getPickerViewText() {
        return S;
    }
}
