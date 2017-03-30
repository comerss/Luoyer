package heimat.com.luoyer.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by code5 on 2017/3/28.
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {
    public P mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter=newPresenter();
        super.onCreate(savedInstanceState);
    }
    public abstract P newPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
