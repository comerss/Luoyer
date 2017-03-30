package heimat.com.luoyer.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by code5 on 2017/3/29.
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment  {
    public P mPresenter;
    public abstract P newPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter=newPresenter();
        super.onCreate(savedInstanceState);
    }

}
