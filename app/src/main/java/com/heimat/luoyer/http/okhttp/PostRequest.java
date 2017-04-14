package com.heimat.luoyer.http.okhttp;

import com.heimat.luoyer.base.BaseBean;

import java.util.Map;

/**
 * Created by code5 on 2017/4/14.
 */
public class PostRequest<T extends BaseBean> extends Request<T> {
    public PostRequest(String url, Class<T> clazz, Map<String, Object> hashMap) {
        super(1, url, clazz, hashMap);
    }

    public PostRequest(String url, Class<T> clazz) {
        super(1, url, clazz);
    }
}
