package com.qiezzi.choseviewlibrary;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qiezzi.choseviewlibrary.bean.Address;
import com.qiezzi.choseviewlibrary.bean.AllCity;
import com.qiezzi.choseviewlibrary.view.BasePickerView;
import com.qiezzi.choseviewlibrary.view.CityWheel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by code5 on 2017/1/4.
 */
public class ChoseCityPicker<T> extends BasePickerView implements View.OnClickListener {
    CityWheel wheelOptions;
    private View btnSubmit, btnCancel;
    private TextView tvTitle;
    private OnGetPositonListener optionsSelectListener;
    private static final String TAG_SUBMIT = "submit";
    private static final String TAG_CANCEL = "cancel";
    private final String mAddressJson=null;
    private Address mAddressArrayList=new Address();

    public ChoseCityPicker(final Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.addess_view, contentContainer);
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
        wheelOptions = new CityWheel(optionspicker);

        //获取数据
        String addressJson = getFromAssets(context,"addr.txt");
        Gson gson = new Gson();
        Log.i("string","address--------->"+addressJson);
        mAddressArrayList = gson.fromJson(addressJson, new TypeToken<Address>(){}.getType());
        wheelOptions.setData(mAddressArrayList.List);
    }
    public ChoseCityPicker(final Context context,ArrayList<AllCity> citys) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.addess_view, contentContainer);
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
        wheelOptions = new CityWheel(optionspicker);
        mAddressArrayList.List=citys;
        //获取数据
        wheelOptions.setData(citys);
    }

    /**
     * 设置选项的单位
     *
     * @param label1 单位
     */
    public void setLabels(String label1) {
        wheelOptions.setLabels(label1, null, null);
    }

    /**
     * 设置选项的单位
     *
     * @param label1 单位
     * @param label2 单位
     */
    public void setLabels(String label1, String label2) {
        wheelOptions.setLabels(label1, label2, null);
    }

    /**
     * 设置选项的单位
     *
     * @param label1 单位
     * @param label2 单位
     * @param label3 单位
     */
    public void setLabels(String label1, String label2, String label3) {
        wheelOptions.setLabels(label1, label2, label3);
    }

    /**
     * 设置是否循环滚动
     *
     * @param cyclic 是否循环
     */
    public void setCyclic(boolean cyclic) {
        wheelOptions.setCyclic(cyclic);
    }

    public void setCyclic(boolean cyclic1, boolean cyclic2, boolean cyclic3) {
        wheelOptions.setCyclic(cyclic1, cyclic2, cyclic3);
    }
    public String getFromAssets(Context context,String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

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
                boolean isOut=optionsCurrentItems[2]<mAddressArrayList.List.get(optionsCurrentItems[0]).city.get(optionsCurrentItems[1]).district.size();
                if (mAddressArrayList != null) {
                    if(mOnGetAddress!=null){
                        mOnGetAddress.getAddress(
                                mAddressArrayList.List.get(optionsCurrentItems[0]).getPickerViewText(),
                                mAddressArrayList.List.get(optionsCurrentItems[0]).city.get(optionsCurrentItems[1]).getPickerViewText(),
                                isOut? mAddressArrayList.List.get(optionsCurrentItems[0]).city.get(optionsCurrentItems[1]).district.get(optionsCurrentItems[2]).D+"":"");
                    }
                    if(mOnGetAddressCode!=null){
                        mOnGetAddressCode.getAddressCode( mAddressArrayList.List.get(optionsCurrentItems[0]).PC,
                                mAddressArrayList.List.get(optionsCurrentItems[0]).city.get(optionsCurrentItems[1]).CC,
                                isOut? mAddressArrayList.List.get(optionsCurrentItems[0]).city.get(optionsCurrentItems[1]).district.get(optionsCurrentItems[2]).DC+"":"");
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
        void getAddress(String province, String city, String area);
    }
    OnGetAddressCode mOnGetAddressCode;

    public void setOnGetAddressCode(OnGetAddressCode onGetAddress) {
        mOnGetAddressCode = onGetAddress;
    }

    public interface OnGetAddressCode {
        void getAddressCode(String PC, String CC, String DC);
    }

}
