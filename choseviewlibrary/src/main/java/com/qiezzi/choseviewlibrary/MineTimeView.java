package com.qiezzi.choseviewlibrary;

import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiezzi.choseviewlibrary.view.BasePickerView;
import com.qiezzi.choseviewlibrary.view.WheelTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间选择器
 * Created by Sai on 15/11/22.
 */
public class MineTimeView extends BasePickerView implements View.OnClickListener {

    private RelativeLayout rlTitle;
    private TextView txSelect;
    WheelTime wheelTime;
    private View btnSubmit, btnCancel;
    private TextView tvTitle;
    private static final String TAG_SUBMIT = "submit";
    private static final String TAG_CANCEL = "cancel";
    private OnTimeSelectListener timeSelectListener;
    private TextView txCancle;
    private TextView txStartTime;
    private TextView txEndTime;
    private SimpleDateFormat mFormater = new SimpleDateFormat("yyyy-MM-dd");
    private int DATE_TYPE = 0;//0为开始时间 1 位结束时间
    private Button btnClear;
    private Button btnSearch;

    public MineTimeView(Context context, TimePickerView.Type type) {
        super(context);

        View view = LayoutInflater.from(context).inflate(R.layout.mine_view_time, contentContainer);

        tvTitle = (TextView) findById(R.id.tvTitle);
        rlTitle = (RelativeLayout) findById(R.id.rlTitle);

        txCancle = (TextView) view.findViewById(R.id.txCancle);
        txStartTime = (TextView) view.findViewById(R.id.txStartTime);
        txEndTime = (TextView) view.findViewById(R.id.txEndTime);
        btnClear = (Button) view.findViewById(R.id.btnClear);
        btnSearch = (Button) view.findViewById(R.id.btnSearch);

        txStartTime.setText(mFormater.format(new Date()));
        txEndTime.setText(mFormater.format(new Date()));
        // ----时间转轮
        final View timepickerview = findById(R.id.timepicker);
        wheelTime = new WheelTime(timepickerview, type);

        //默认选中当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        wheelTime.setPicker(year, month, day, hours, minute);
        txStartTime.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DATE_TYPE = 0;
                try {
                    setTime(mFormater.parse(txStartTime.getText().toString().trim()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                txStartTime.setTextColor(getResources().getColor(R.color.color_lable));
                txEndTime.setTextColor(getResources().getColor(R.color.color_editText));
            }
        });
        txEndTime.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DATE_TYPE = 1;
                try {
                    setTime(mFormater.parse(txEndTime.getText().toString().trim()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                txStartTime.setTextColor(getResources().getColor(R.color.color_editText));
                txEndTime.setTextColor(getResources().getColor(R.color.color_lable));
            }
        });
        txCancle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                txStartTime.setText(mFormater.format(new Date()));
                txEndTime.setText(mFormater.format(new Date()));
            }
        });
        btnSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnSearchClickListener.onSearchCLick(txStartTime.getText().toString().trim(),
                        txEndTime.getText().toString().trim());
                dismiss();
            }
        });
        wheelTime.setOnTimeChangListener(new WheelTime.OnTimeChangeListener() {
            @Override
            public void onTimeChanged(String time) {
                try {
                    if (DATE_TYPE == 0) {
                        //开始时间
                        txStartTime.setText(mFormater.format(WheelTime.dateFormat.parse(wheelTime.getTime())) + "");
                    } else {
                        //结束时间
                        txEndTime.setText(mFormater.format(WheelTime.dateFormat.parse(wheelTime.getTime())) + "");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 设置可以选择的时间范围
     * 要在setTime之前调用才有效果
     *
     * @param startYear 开始年份
     * @param endYear   结束年份
     */
    public void setRange(int startYear, int endYear) {
        wheelTime.setStartYear(startYear);
        wheelTime.setEndYear(endYear);
    }

    public void setTitleVisibility(boolean visibility) {
        if (visibility) {
            rlTitle.setVisibility(View.VISIBLE);
        } else {
            rlTitle.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 设置选中时间
     *
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


    /**
     * 设置是否循环滚动
     *
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
                    Date date = WheelTime.dateFormat.parse(wheelTime.getTime());
                    timeSelectListener.onTimeSelect(date);
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

    private OnSearchClickListener mOnSearchClickListener;

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public interface OnSearchClickListener {
        void onSearchCLick(String startTime, String endTime);
    }

    public void setOnSearchClick(OnSearchClickListener onSearchClickListener) {
        mOnSearchClickListener = onSearchClickListener;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            dismiss();
            Log.i("MineTimePicker","返回退出view");
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
