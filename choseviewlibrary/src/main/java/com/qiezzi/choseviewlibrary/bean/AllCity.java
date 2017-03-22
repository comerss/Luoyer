package com.qiezzi.choseviewlibrary.bean;

import com.qiezzi.choseviewlibrary.model.IPickerViewData;

import java.util.ArrayList;

/**
 * Created by code5 on 2017/1/12.
 */
public class AllCity implements IPickerViewData {
    public String P;
    public String PC;
    public ArrayList<City> city;

    @Override
    public String toString() {
        /*"AllCity{" +
                "P='" + P + '\'' +
                ", PC='" + PC + '\'' +
                ", city=" + city +
                '}'*/
        return ""+'{'+'"'+
                "P"+'"'+':'+'"' + P + '"' +
                ","+'"'+"PC"+'"'+':' +'"'+ PC +'"'+
                ","+'"'+ "city"+'"'+':'+city +
                '}';
    }

    @Override
    public String getPickerViewText() {
        return P;
    }
}