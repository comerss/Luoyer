package heimat.com.luoyer.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import heimat.com.luoyer.interfaces.InitInterface;

/**
 * Created by code5 on 2017/3/29.
 */
public abstract class BaseFragment extends Fragment implements InitInterface{

    private View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBefore();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(),null);
        initView();
        initListener();
        initData();
        return rootView;
    }

    /**
     * 短Toast 弹窗
     *
     * @param s
     */
    public void showToast(final String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    /**
     * activity跳转
     *
     * @param activity
     * @param <E>
     */
    public <E> void toActivity(Class<E> activity) {
        Intent intent = new Intent(getActivity(), activity);
        startActivity(intent);
    }

    /**
     * activity跳转
     *
     * @param activity
     * @param <E>
     */
    public <E> void toActivity(Class<E> activity, Bundle extras) {
        Intent intent = new Intent(getActivity(), activity);
        intent.putExtras(extras);
        startActivity(intent);
    }

    /**
     * activity跳转(返回结果)
     *
     * @param activity
     * @param <E>
     */
    public <E> void toActivityForResult(Class<E> activity, Bundle extras,
                                        int requestCode) {
        Intent intent = new Intent(getActivity(), activity);
        intent.putExtras(extras);
        startActivityForResult(intent, requestCode);
    }
}
