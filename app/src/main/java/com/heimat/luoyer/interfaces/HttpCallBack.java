package com.heimat.luoyer.interfaces;

import com.heimat.luoyer.base.BaseBean;

/**
 * Created by code5 on 2017/4/10.
 * @param T 这里所有的请求的结果都集成baseBean当然可以根据需求更改，也可以不继承！
 */
public interface HttpCallBack<T extends BaseBean> {
    /**
     * 访问网络成功
     */
    void onSuccess(int requestCode,String json, T t);

    /**
     * 有错的类型请求
     */
    void onError(int errorCode, String errorMessage);
}
