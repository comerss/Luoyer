package com.heimat.luoyer.ui.main;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.heimat.luoyer.base.BaseActivity;
import com.heimat.luoyer.ui.zhihu.HuListFragment;

import butterknife.BindView;
import heimat.com.luoyer.R;

/**
 * Created by code5 on 2017/3/31.
 */
public class TopLineActivity extends BaseActivity {
    @BindView(R.id.slindTab)
    SlidingTabLayout mSlindTab;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    String[] titles = new String[]{"今日看点", "叶落无声", "三生三世"};
    @BindView(R.id.txBack)
    TextView mTxBack;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    private HuListFragment mHuListFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_top_line;
    }

    @Override
    public void initView() {
        mViewPager.setAdapter(new MinePagerAdapter(getSupportFragmentManager()));
        mSlindTab.setViewPager(mViewPager);
        mSlindTab.setCurrentTab(0);
    }

    @Override
    public void initListener() {
        mTxBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void initData() {

    }

    class MinePagerAdapter extends FragmentPagerAdapter {

        public MinePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.i("Position", "选择的位置==Postion" + position);
            mHuListFragment = new HuListFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("Status", position);
            mHuListFragment.setArguments(bundle);
            return mHuListFragment;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}
