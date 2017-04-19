package com.heimat.luoyer.ui.zhihu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.flyco.tablayout.SlidingTabLayout;
import com.heimat.luoyer.R;
import com.heimat.luoyer.base.BaseFragment;
import com.heimat.luoyer.ui.zhihu.fragment.HuListFragment;

import butterknife.BindView;

/**
 * Created by code5 on 2017/3/29.
 */
public class HuMainFragment extends BaseFragment {
    @BindView(R.id.slindTab)
    SlidingTabLayout mSlindTab;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    String[] titles = new String[]{"今日看点", "叶落无声", "三生三世"};
    private HuListFragment mHuListFragment;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_hu_main;
    }

    @Override
    public void initView() {
        mViewPager.setAdapter(new MinePagerAdapter(getChildFragmentManager()));
        mSlindTab.setViewPager(mViewPager);
        mSlindTab.setCurrentTab(0);
    }

    @Override
    public void initListener() {
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
            Bundle bundle=new Bundle();
            bundle.putInt("Position",position);
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
