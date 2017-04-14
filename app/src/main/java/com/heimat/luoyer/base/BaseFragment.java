package com.heimat.luoyer.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.heimat.luoyer.interfaces.InitInterface;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by code5 on 2017/3/29.
 */
public abstract class BaseFragment extends Fragment implements InitInterface {

    private View rootView;
    private Unbinder mUnBinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBefore();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(),null);
        mUnBinder = ButterKnife.bind(this,rootView);
        initView();
        initListener();
        initData();
        return rootView;
    }
    @Override
    public void initBefore() {
        //  已经做了空实现，如果子类不需要实现，就可以不重写！
    }

    @Override
    public void initView() {
        //  已经做了空实现，如果子类不需要实现，就可以不重写！
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }
}
