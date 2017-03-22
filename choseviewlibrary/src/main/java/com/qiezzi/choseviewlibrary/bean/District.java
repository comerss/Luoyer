package com.qiezzi.choseviewlibrary.bean;

import com.qiezzi.choseviewlibrary.model.IPickerViewData;

import java.util.ArrayList;

/**
 * Created by code5 on 2017/1/12.
 */
public class District implements IPickerViewData {
    public String D;
    public String DC;

    @Override
    public String toString() {
        return /*"District{" +
                "D='" + D + '\'' +
                ", DC='" + DC + '\'' +
                ", Street=" + Street +
                '}'*/
               ""+'{'+'"'+"D"+'"'+':'+'"'+D+'"'+","+'"'+"DC"+'"'+':'+'"'+DC+'"'+'}'
                ;
    }

    public void setStreet(ArrayList<com.qiezzi.choseviewlibrary.bean.Street> street) {
        Street = street;
    }

    public ArrayList<com.qiezzi.choseviewlibrary.bean.Street> Street;
    @Override
    public String getPickerViewText() {
        return D;
    }
}
