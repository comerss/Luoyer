package com.qiezzi.choseviewlibrary;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.qiezzi.choseviewlibrary.bean.Address;
import com.qiezzi.choseviewlibrary.bean.AllCity;
import com.qiezzi.choseviewlibrary.view.BasePickerView;
import com.qiezzi.choseviewlibrary.view.Level2City;

import java.util.ArrayList;

/**
 * Created by code5 on 2017/3/7.
 */
public class Level2CityPicker extends BasePickerView implements View.OnClickListener {
    Level2City wheelOptions;
    private View btnSubmit, btnCancel;
    private TextView tvTitle;
    private OnGetPositonListener optionsSelectListener;
    private static final String TAG_SUBMIT = "submit";
    private static final String TAG_CANCEL = "cancel";
    private final String mAddressJson=null;
    private Address mAddressArrayList=new Address();

    public Level2CityPicker(final Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.addess2_view, contentContainer);
        // -----确定和取消按钮
        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnSubmit.setTag(TAG_SUBMIT);
        btnCancel = view.findViewById(R.id.btnCancel);
        btnCancel.setTag(TAG_CANCEL);
        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        //顶部标题
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        // ----转轮
        final View optionspicker = view.findViewById(R.id.optionspicker);
        wheelOptions = new Level2City(optionspicker);

    }


    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        if (tag.equals(TAG_CANCEL)) {
            dismiss();
            return;
        } else {
            if (mOnGetAddress != null) {
                int[] optionsCurrentItems = wheelOptions.getCurrentItems();
                if (mAddressArrayList != null) {
                    if(mOnGetAddress!=null){
                        mOnGetAddress.getAddress(
                                mAddressArrayList.List.get(optionsCurrentItems[0]).P,
                                mAddressArrayList.List.get(optionsCurrentItems[0]).city.get(optionsCurrentItems[1]).C);
                    }
                    if(mOnGetAddressCode!=null){
                        mOnGetAddressCode.getAddressCode( mAddressArrayList.List.get(optionsCurrentItems[0]).PC,
                                mAddressArrayList.List.get(optionsCurrentItems[0]).city.get(optionsCurrentItems[1]).CC);
                    }
                    if(optionsSelectListener!=null){
                        optionsSelectListener.onGetPostion(optionsCurrentItems[0],optionsCurrentItems[1],optionsCurrentItems[2]);
                    }
                }
            }
            dismiss();
            return;
        }
    }

    public interface OnGetPositonListener {
        void onGetPostion(int province, int city, int area);
    }

    public void setOnGetPositionListener(
            OnGetPositonListener optionsSelectListener) {
        this.optionsSelectListener = optionsSelectListener;
    }

    public void setTitle(String title) {
        tvTitle.setText(title);

    }

    OnGetAddress mOnGetAddress;

    public void setOnGetAddress(OnGetAddress onGetAddress) {
        mOnGetAddress = onGetAddress;
    }

    public interface OnGetAddress {
        void getAddress(String province, String city);
    }
    OnGetAddressCode mOnGetAddressCode;

    public void setOnGetAddressCode(OnGetAddressCode onGetAddress) {
        mOnGetAddressCode = onGetAddress;
    }

    public interface OnGetAddressCode {
        void getAddressCode(String PC, String CC);
    }
    public void setData(ArrayList<AllCity> list){
        mAddressArrayList.List=list;
        wheelOptions.setData(mAddressArrayList.List);
    }
}
