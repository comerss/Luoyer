package com.heimat.luoyer.ui.zhihu;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heimat.luoyer.R;
import com.heimat.luoyer.base.BaseMvpActivity;
import com.heimat.luoyer.ui.zhihu.fragment.FocusFragment;
import com.heimat.luoyer.ui.zhihu.fragment.MiFragment;
import com.heimat.luoyer.ui.zhihu.fragment.VideoFragment;

import butterknife.BindView;

/**
 * Created by code5 on 2017/3/29.
 */
public class HuActivity extends BaseMvpActivity<HuPresenter> implements HuInterfaces.HuView {

    @BindView(R.id.mViewPager)
    LinearLayout mViewPager;
    @BindView(R.id.ivIconHome)
    ImageView mIvIconHome;
    @BindView(R.id.tvTextHome)
    TextView mTvTextHome;
    @BindView(R.id.tvBadgeHome)
    TextView mTvBadgeHome;
    @BindView(R.id.ivIconVideo)
    ImageView mIvIconVideo;
    @BindView(R.id.tvTextVideo)
    TextView mTvTextVideo;
    @BindView(R.id.ivIconAttention)
    ImageView mIvIconAttention;
    @BindView(R.id.tvTextAttention)
    TextView mTvTextAttention;
    @BindView(R.id.ivIconMe)
    ImageView mIvIconMe;
    @BindView(R.id.tvTextMe)
    TextView mTvTextMe;
    private HuMainFragment mHuMainFragment;
    @BindView(R.id.llBottom)
    LinearLayout mLyBottom;
    private View lastSelectedIcon;
    private View lastSelectedText;
    private VideoFragment mVideoFragment;
    private FocusFragment mFocusFragment;
    private MiFragment mMiFragment;

    @Override
    public HuPresenter newPresenter() {
        return new HuPresenter(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main_hu;
    }

    @Override
    public void initView() {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (mHuMainFragment == null) {
            mHuMainFragment = new HuMainFragment();
        }
        transaction.replace(R.id.mViewPager, mHuMainFragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void initListener() {
        for (int i = 0; i < mLyBottom.getChildCount(); i++) {
            if (i == 0) {
                //默认选中首页
                setSelectIcon(mIvIconHome, mTvTextHome);
            }
            final int position = i;
            mLyBottom.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lastSelectedIcon != null) lastSelectedIcon.setSelected(false);
                    if (lastSelectedText != null) lastSelectedText.setSelected(false);
                    RelativeLayout rl = (RelativeLayout) v;
                    ImageView icon = (ImageView) rl.getChildAt(0);
                    TextView text = (TextView) rl.getChildAt(1);
                    showFragment(position);
                    setSelectIcon(icon, text);
                }
            });
        }
    }


    @Override
    public void initData() {

    }

    private void setSelectIcon(ImageView iv, TextView tv) {
        iv.setSelected(true);
        tv.setSelected(true);
        lastSelectedIcon = iv;
        lastSelectedText = tv;
    }

    private void showFragment(int position) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (mHuMainFragment == null) {
                    mHuMainFragment = new HuMainFragment();
                }
                transaction.replace(R.id.mViewPager, mHuMainFragment);
                break;
            case 1:
                if(mVideoFragment==null){
                    mVideoFragment = new VideoFragment();
                }
                transaction.replace(R.id.mViewPager, mVideoFragment);
                break;
            case 2:
                if(mFocusFragment==null){
                    mFocusFragment = new FocusFragment();
                }
                transaction.replace(R.id.mViewPager, mFocusFragment);
                break;
            case 3:
                if(mMiFragment==null){
                    mMiFragment = new MiFragment();
                }
                transaction.replace(R.id.mViewPager, mMiFragment);
                break;
        }
        transaction.commitAllowingStateLoss();
    }
}
