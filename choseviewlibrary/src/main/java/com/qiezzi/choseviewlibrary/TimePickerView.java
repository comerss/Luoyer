package com.qiezzi.choseviewlibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiezzi.choseviewlibrary.view.BasePickerView;
import com.qiezzi.choseviewlibrary.view.WheelTime;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间选择器
 * Created by Sai on 15/11/22.
 */
public class TimePickerView extends BasePickerView implements View.OnClickListener {

    private  RelativeLayout rlTitle;

    public enum Type {
        ALL, YEAR_MONTH_DAY, HOURS_MINS, MONTH_DAY_HOUR_MIN , YEAR_MONTH,CUSTOM
    }// 四种选择模式，年月日时分，年月日，时分，月日时分

    WheelTime wheelTime;
    private View btnSubmit, btnCancel;
    private TextView tvTitle;
    private static final String TAG_SUBMIT = "submit";
    private static final String TAG_CANCEL = "cancel";
    private OnTimeSelectListener timeSelectListener;
    private Type mType;
    public TimePickerView(Context context, Type type) {
        super(context);
        mType=type;
        LayoutInflater.from(context).inflate(R.layout.pickerview_time, contentContainer);
        // -----确定和取消按钮
        btnSubmit = findById(R.id.btnSubmit);
        btnSubmit.setTag(TAG_SUBMIT);
        btnCancel = findById(R.id.btnCancel);
        btnCancel.setTag(TAG_CANCEL);
        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        //顶部标题
        tvTitle = (TextView) findById(R.id.tvTitle);
        rlTitle = (RelativeLayout) findById(R.id.rlTitle);
        // ----时间转轮
        final View timepickerview = findById(R.id.timepicker);
        wheelTime = new WheelTime(timepickerview, type);

        //默认选中当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        wheelTime.setPicker(year, month, day, hours, minute);
//        wheelTime.setOnTimeChangListener(new WheelTime.OnTimeChangeListener() {
//            @Override
//            public void onTimeChanged(String time) {
//                mOnGetChangedTimeListener.onGetChagedTime(time);
//            }
//        });
    }

    /**
     * 设置可以选择的时间范围
     * 要在setTime之前调用才有效果
     * @param startYear 开始年份
     * @param endYear 结束年份
     */
    public void setRange(int startYear, int endYear) {
        wheelTime.setStartYear(startYear);
        wheelTime.setEndYear(endYear);
    }
    public void setTitleVisibility(boolean visibility){
        if(visibility){
            rlTitle.setVisibility(View.VISIBLE);
        }else{
            rlTitle.setVisibility(View.INVISIBLE);
        }
    }
    /**
     * 设置选中时间
     * @param date 时间
     */
    public void setTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date == null)
            calendar.setTimeInMillis(System.currentTimeMillis());
        else
            calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        wheelTime.setPicker(year, month, day, hours, minute);
    }

//    /**
//     * 指定选中的时间，显示选择器
//     *
//     * @param date
//     */
//    public void show(Date date) {
//        Calendar calendar = Calendar.getInstance();
//        if (date == null)
//            calendar.setTimeInMillis(System.currentTimeMillis());
//        else
//            calendar.setTime(date);
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        int hours = calendar.get(Calendar.HOUR_OF_DAY);
//        int minute = calendar.get(Calendar.MINUTE);
//        wheelTime.setPicker(year, month, day, hours, minute);
//        show();
//    }

    /**
     * 设置是否循环滚动
     * @param cyclic 是否循环
     */
    public void setCyclic(boolean cyclic) {
        wheelTime.setCyclic(cyclic);
    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        if (tag.equals(TAG_CANCEL)) {
            dismiss();
            return;
        } else {
            if (timeSelectListener != null) {
                try {
                    if(mType==Type.CUSTOM){
                        Date date = WheelTime.mFormat.parse(wheelTime.getTime());
                        timeSelectListener.onTimeSelect(date);
                    }else{
                        Date date = WheelTime.dateFormat.parse(wheelTime.getTime());
                        timeSelectListener.onTimeSelect(date);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            dismiss();
            return;
        }
    }

    public interface OnTimeSelectListener {
        void onTimeSelect(Date date);
    }

    public void setOnTimeSelectListener(OnTimeSelectListener timeSelectListener) {
        this.timeSelectListener = timeSelectListener;
    }

    public void setTitle(String title){
        tvTitle.setText(title);
    }
    private OnGetChangedTimeListener mOnGetChangedTimeListener;
    public interface OnGetChangedTimeListener{
        void onGetChagedTime(String time);
    }
    public void setOnGetChangedTime(OnGetChangedTimeListener onGetChangedTimeListener){
        mOnGetChangedTimeListener=onGetChangedTimeListener;
    }
}
