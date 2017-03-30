package heimat.com.luoyer.ui.zhihu;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import heimat.com.luoyer.R;
import heimat.com.luoyer.base.BaseFragment;

/**
 * Created by code5 on 2017/3/29.
 */
public class HuMainFragment extends BaseFragment {
    @BindView(R.id.slindTab)
    SlidingTabLayout mSlindTab;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    String[] titles = new String[]{"今日看点", "叶落无声", "三生三世"};
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_hu_main;
    }

    @Override
    public void initView() {
        mViewPager.setAdapter(new MinePagerAdapter(getActivity().getSupportFragmentManager()));
        mSlindTab.setViewPager(mViewPager);
        mSlindTab.setCurrentTab(0);
//        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void initListener() {
        mSlindTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                Log.i("Position", "------>" + position);
                mSlindTab.setCurrentTab(position);
            }

            @Override
            public void onTabReselect(int position) {
                Log.i("unPosition", "---un--->" + position);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    class MinePagerAdapter extends FragmentPagerAdapter {

        public MinePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.i("Position", "选择的位置==Postion" + position);
            return new HuListFragment();
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
