package com.qiezzi.choseviewlibrary.view;

import android.view.View;

import com.qiezzi.choseviewlibrary.R;
import com.qiezzi.choseviewlibrary.adapter.ArrayWheelAdapter;
import com.qiezzi.choseviewlibrary.bean.AllCity;
import com.qiezzi.choseviewlibrary.lib.AddressView;
import com.qiezzi.choseviewlibrary.listener.OnItemSelectedListener;

import java.util.ArrayList;

public class CityWheel<T> {
    private View view;
    private AddressView wv_option1;
    private AddressView wv_option2;
    private AddressView wv_option3;
    private ArrayList<AllCity> citys;
    private boolean linkage = false;
    private OnItemSelectedListener wheelListener_option1;
    private OnItemSelectedListener wheelListener_option2;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public CityWheel(View view) {
        super();
        this.view = view;
        setView(view);
    }

    public void setData(ArrayList<AllCity> data) {
        citys = data;
        // 选项1
        wv_option1 = (AddressView) view.findViewById(R.id.options1);
        wv_option1.setAdapter(new ArrayWheelAdapter(citys));// 设置显示数据
        wv_option1.setCurrentItem(0);// 初始化时显示的数据
        // 选项2
        wv_option2 = (AddressView) view.findViewById(R.id.options2);
        wv_option2.setAdapter(new ArrayWheelAdapter(citys.get(0).city));// 设置显示数据
        wv_option2.setCurrentItem(wv_option1.getCurrentItem());// 初始化时显示的数据
        // 选项3
        wv_option3 = (AddressView) view.findViewById(R.id.options3);
        wv_option3.setAdapter(new ArrayWheelAdapter(citys.get(0).city.get(0).district));// 设置显示数据
        wv_option3.setCurrentItem(wv_option3.getCurrentItem());// 初始化时显示的数据

        int textSize = 20;
        wv_option1.setTextSize(textSize);
        wv_option2.setTextSize(textSize);
        wv_option2.setVisibility(View.VISIBLE);
        wv_option3.setVisibility(View.VISIBLE);
        wv_option3.setTextSize(textSize);

        // 联动监听器
        wheelListener_option1 = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                index=index>=citys.size()-1?citys.size()-1:index;
                int opt2Select = 0;
                opt2Select = wv_option2.getCurrentItem();//上一个opt2的选中位置
                //新opt2的位置，判断如果旧位置没有超过数据范围，则沿用旧位置，否则选中最后一项
                opt2Select = opt2Select >= citys.size() - 1 ? citys.size() - 1 : opt2Select;
                wv_option2.setAdapter(new ArrayWheelAdapter(citys.get(index).city));
                wv_option2.setCurrentItem(0);
                wheelListener_option2.onItemSelected(0);
                if(citys.get(index).city.get(0).district!=null){
                    wv_option3.setAdapter(new ArrayWheelAdapter(citys.get(index).city.get(0).district));
                    wv_option3.setCurrentItem(0);
                }else{
                    wv_option3.setAdapter(new ArrayWheelAdapter(new ArrayList()));
                }
            }
        };
        wheelListener_option2 = new OnItemSelectedListener() {

            @Override
            public void onItemSelected(int index) {
//                index = index >= citys.get(wv_option2.getCurrentItem()).getCity().size() - 1 ? citys.get(wv_option2.getCurrentItem()).getCity().size() - 1 : index;
                int opt1Select = 0;
                opt1Select = wv_option1.getCurrentItem();//上一个opt2的选中位置
                //新opt2的位置，判断如果旧位置没有超过数据范围，则沿用旧位置，否则选中最后一项
                opt1Select = opt1Select >= citys.size() - 1 ? citys.size() - 1 : opt1Select;
                int opt2Select = 0;
                opt2Select = wv_option2.getCurrentItem();//上一个opt2的选中位置
                //新opt2的位置，判断如果旧位置没有超过数据范围，则沿用旧位置，否则选中最后一项
                opt2Select = opt2Select >= citys.get(opt1Select).city.size() - 1 ? citys.get(opt1Select).city.size() - 1 : opt2Select;

                if(citys.get(opt1Select).city.get(opt2Select).district!=null){
                    wv_option3.setAdapter(new ArrayWheelAdapter(citys.get(opt1Select).city.get(opt2Select).district));
                    wv_option3.setCurrentItem(0);
                }else{
                    wv_option3.setAdapter(new ArrayWheelAdapter(new ArrayList()));
                }
            }
        };

        wv_option1.setOnItemSelectedListener(wheelListener_option1);
        wv_option2.setOnItemSelectedListener(wheelListener_option2);
    }

    /**
     * 设置选项的单位
     *
     * @param label1 单位
     * @param label2 单位
     * @param label3 单位
     */
    public void setLabels(String label1, String label2, String label3) {
        if (label1 != null)
            wv_option1.setLabel(label1);
        if (label2 != null)
            wv_option2.setLabel(label2);
        if (label3 != null)
            wv_option3.setLabel(label3);
    }

    /**
     * 设置是否循环滚动
     *
     * @param cyclic 是否循环
     */
    public void setCyclic(boolean cyclic) {
        wv_option1.setCyclic(cyclic);
        wv_option2.setCyclic(cyclic);
        wv_option3.setCyclic(cyclic);
    }

    /**
     * 分别设置第一二三级是否循环滚动
     *
     * @param cyclic1,cyclic2,cyclic3 是否循环
     */
    public void setCyclic(boolean cyclic1, boolean cyclic2, boolean cyclic3) {
        wv_option1.setCyclic(cyclic1);
        wv_option2.setCyclic(cyclic2);
        wv_option3.setCyclic(cyclic3);
    }

    /**
     * 设置第二级是否循环滚动
     *
     * @param cyclic 是否循环
     */
    public void setOption2Cyclic(boolean cyclic) {
        wv_option2.setCyclic(cyclic);
    }

    /**
     * 设置第三级是否循环滚动
     *
     * @param cyclic 是否循环
     */
    public void setOption3Cyclic(boolean cyclic) {
        wv_option3.setCyclic(cyclic);
    }

    /**
     * 返回当前选中的结果对应的位置数组 因为支持三级联动效果，分三个级别索引，0，1，2
     *
     * @return 索引数组
     */
    public int[] getCurrentItems() {
        int[] currentItems = new int[3];
        currentItems[0] = wv_option1.getCurrentItem();
        currentItems[1] = wv_option2.getCurrentItem();
        currentItems[2] = wv_option3.getCurrentItem();
        return currentItems;
    }

}
