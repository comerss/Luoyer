package heimat.com.luoyer.ui.zhihu;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import heimat.com.luoyer.R;
import heimat.com.luoyer.base.BaseMvpActivity;

/**
 * Created by code5 on 2017/3/29.
 */
public class HuActivity extends BaseMvpActivity<HuPresenter> implements HuInterfaces.HuView {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.navigationBar)
    BottomNavigationView mNavigationBar;
    @BindView(R.id.mViewPager)
    LinearLayout mViewPager;
    private HuMainFragment mHuMainFragment;

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
        mToolbar.setTitle("知乎日报");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        mNavigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.home:
//                        if (mHuMainFragment == null) {
                            mHuMainFragment = new HuMainFragment();
//                        }
                        transaction.replace(R.id.mViewPager, mHuMainFragment);
                        break;
                    case R.id.focus:
                        break;
                    case R.id.video:
                        break;
                    case R.id.me:
                        break;
                }
                transaction.commitAllowingStateLoss();
                return true;
            }
        });
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void initData() {

    }

}
