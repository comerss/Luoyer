package heimat.com.luoyer.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import heimat.com.luoyer.interfaces.InitInterface;

/**
 * Created by code5 on 2017/3/22.
 */
public abstract class BaseActivity extends AppCompatActivity implements InitInterface {

    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initBefore();
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnBinder = ButterKnife.bind(this);
        initView();
        initListener();
        initData();
    }

    /**
     * 短Toast 弹窗
     *
     * @param s
     */
    public void showToast(final String s) {
        Toast.makeText(BaseActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    /**
     * activity跳转
     *
     * @param activity
     * @param <E>
     */
    public <E> void toActivity(Class<E> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        //overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
    }

    /**
     * activity跳转
     *
     * @param activity
     * @param <E>
     */
    public <E> void toActivity(Class<E> activity, Bundle extras) {
        Intent intent = new Intent(this, activity);
        intent.putExtras(extras);
        startActivity(intent);
        //overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
    }

    /**
     * activity跳转(返回结果)
     *
     * @param activity
     * @param <E>
     */
    public <E> void toActivityForResult(Class<E> activity, Bundle extras,
                                        int requestCode) {
        Intent intent = new Intent(this, activity);
        intent.putExtras(extras);
        startActivityForResult(intent, requestCode);
        //overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
    }

    /**
     * 隐藏键盘
     *
     * @param mcontext
     * @param v
     */
    public void hideSystemKeyBoard(Activity mcontext, View v) {
        InputMethodManager imm = (InputMethodManager) mcontext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }
}
