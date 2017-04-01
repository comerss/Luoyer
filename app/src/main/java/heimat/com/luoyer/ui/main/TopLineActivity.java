package heimat.com.luoyer.ui.main;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.flyco.tablayout.SlidingTabLayout;

import butterknife.BindView;
import heimat.com.luoyer.R;
import heimat.com.luoyer.base.BaseActivity;
import heimat.com.luoyer.ui.zhihu.HuListFragment;

/**
 * Created by code5 on 2017/3/31.
 */
public class TopLineActivity extends BaseActivity {
    @BindView(R.id.slindTab)
    SlidingTabLayout mSlindTab;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    String[] titles = new String[]{"今日看点", "叶落无声", "三生三世"};
    private HuListFragment mHuListFragment;
    private AppBarLayout mAppbar;

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
            bundle.putInt("Status",position);
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
