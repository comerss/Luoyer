package com.qiezzi.choseviewlibrary.view;

import android.view.View;

import com.qiezzi.choseviewlibrary.R;
import com.qiezzi.choseviewlibrary.adapter.ArrayWheelAdapter;
import com.qiezzi.choseviewlibrary.bean.AllCity;
import com.qiezzi.choseviewlibrary.lib.AddressView;
import com.qiezzi.choseviewlibrary.listener.OnItemSelectedListener;

import java.util.ArrayList;

/**
 * Created by code5 on 2017/3/7.
 */
public class Level2City  {
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

        public Level2City(View view) {
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
            int textSize = 20;
            wv_option1.setTextSize(textSize);
            wv_option2.setTextSize(textSize);
            wv_option2.setVisibility(View.VISIBLE);
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

                }
            };
            wheelListener_option2 = new OnItemSelectedListener() {

                @Override
                public void onItemSelected(int index) {
//
                }
            };
            wv_option1.setOnItemSelectedListener(wheelListener_option1);
        }

        /**
         * 返回当前选中的结果对应的位置数组 因为支持三级联动效果，分三个级别索引，0，1，2
         *
         * @return 索引数组
         */
        public int[] getCurrentItems() {
            int[] currentItems = new int[2];
            currentItems[0] = wv_option1.getCurrentItem();
            currentItems[1] = wv_option2.getCurrentItem();
            return currentItems;
        }

}

