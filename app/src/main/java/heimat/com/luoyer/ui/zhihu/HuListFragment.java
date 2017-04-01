package heimat.com.luoyer.ui.zhihu;

import android.widget.TextView;

import butterknife.BindView;
import heimat.com.luoyer.R;
import heimat.com.luoyer.base.BaseMvpFragment;

/**
 * Created by code5 on 2017/3/30.
 */
public class HuListFragment extends BaseMvpFragment<HuMainPresenter> implements HuInterfaces.HuMainView {

    @BindView(R.id.txContent)
    TextView mTxContent;
    private int mStatus;

    @Override
    public void initBefore() {
        if(getArguments()!=null){
            mStatus = getArguments().getInt("Status");
        }
    }

    @Override
    public HuMainPresenter newPresenter() {
        return new HuMainPresenter(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_hu_list;
    }


    @Override
    public void initView() {
        mTxContent.setText("我是第---》" + mStatus + "  页");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void showData() {

    }

    @Override
    public void showLoadMore() {

    }

}
