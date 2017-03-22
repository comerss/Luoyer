package com.qiezzi.choseviewlibrary.bean;

import com.qiezzi.choseviewlibrary.model.IPickerViewData;

import java.util.ArrayList;

/**
 * Created by code5 on 2017/1/12.
 */
public class City implements IPickerViewData {
    public String C;
    public String CC;
    public ArrayList<District> district;

    @Override
    public String toString() {
        return /*"City{" +
                "C='" + C + '\'' +
                ", CC='" + CC + '\'' +
                ", district=" + district +
                '}'*/
                ""+'{'+'"'+"C"+'"'+':'+'"'+C+'"'+","
                        +'"'+"CC"+'"'+':'+'"'+CC+'"'+","
                +'"'+"district"+'"'+':'+district+'}'
                ;
    }

    @Override
    public String getPickerViewText() {
        return C;
    }
}
