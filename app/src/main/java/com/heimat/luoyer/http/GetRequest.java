package com.heimat.luoyer.http;

import com.heimat.luoyer.base.BaseBean;

/**
 * Created by code5 on 2017/4/14.
 */
public class GetRequest<T extends BaseBean> extends Request<T> {
    public GetRequest( String url, Class<T> clazz) {
        super(0, url, clazz);
    }
}
