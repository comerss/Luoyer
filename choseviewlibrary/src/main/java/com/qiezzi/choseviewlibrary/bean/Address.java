package com.qiezzi.choseviewlibrary.bean;

import java.util.ArrayList;

/**
 * Created by code5 on 2017/1/12.
 */
public class Address {
    public ArrayList<AllCity> List;

    @Override
    public String toString() {
        return  ""+'{' +'"'+
                "List"+'"'+':'+ List +
                '}';
    }
}
