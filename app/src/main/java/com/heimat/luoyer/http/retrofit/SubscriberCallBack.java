package com.heimat.luoyer.http.retrofit;

/**
 * Created by Administrator
 * on 2016/5/18.
 */
public abstract class SubscriberCallBack<T> extends BaseCallBack<T> {


    @Override
    public void onNext(T response) {
        onSuccess(response);//对返回的逻辑进行判断 ，onFial。。。。。。
    }

    protected abstract void onSuccess(T response);

    @Override
    public void onCompleted() {
        super.onCompleted();
        dismissDialog();
    }

    protected  void dismissDialog(){
        //diloag dismiss
    }

    protected  void showDialog(){
        //dialog show
    }

    @Override
    public void onStart() {
        super.onStart();
        showDialog();
    }
}
