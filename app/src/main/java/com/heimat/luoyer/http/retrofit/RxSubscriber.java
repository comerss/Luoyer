package com.heimat.luoyer.http.retrofit;

import android.app.Activity;
import android.content.Context;

import com.heimat.luoyer.R;
import com.heimat.luoyer.utils.NetWorkUtils;
import com.heimat.luoyer.utils.UIUtils;
import com.heimat.luoyer.widget.LoadingDialog;

import java.net.SocketException;

import rx.Subscriber;

/**
 * Created by code5 on 2017/4/15.
 * 含有loading框的加载回调！
 *
 */
public abstract class RxSubscriber<T> extends Subscriber<T> {

    private Context mContext;
    private String msg;
    private boolean showDialog=true;

    /**
     * 是否显示浮动dialog
     */
    public RxSubscriber<T> isShowDialog(boolean ShowDialog) {
        this.showDialog= ShowDialog;
        return this;
    }
    public RxSubscriber(Context context, String msg,boolean showDialog) {
        this.mContext = context;
        this.msg = msg;
        this.showDialog=showDialog;
    }
    public RxSubscriber(Context context) {
        this(context, UIUtils.getContext().getString(R.string.loading),true);
    }
    public RxSubscriber(Context context,boolean showDialog) {
        this(context, UIUtils.getContext().getString(R.string.loading),showDialog);
    }

    @Override
    public void onCompleted() {
        if (showDialog)
            LoadingDialog.dismiss();
    }
    @Override
    public void onStart() {
        super.onStart();
        if (showDialog) {
            try {
                LoadingDialog.show((Activity) mContext,msg,true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onNext(T t) {
        _onNext(t);
    }
    @Override
    public void onError(Throwable e) {
        if (showDialog)
            LoadingDialog.dismiss();
        e.printStackTrace();
        //网络
        if (!NetWorkUtils.isNetConnected(UIUtils.getContext())) {
            _onError(UIUtils.getContext().getString(R.string.no_net));
        }
        //服务器
        else if (e instanceof SocketException) {
            _onError(e.getMessage());
        }
        //其它
        else {
            _onError(UIUtils.getContext().getString(R.string.net_error));
        }
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);

}
