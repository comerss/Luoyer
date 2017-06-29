package com.heimat.luoyer.http.okhttp;

import com.heimat.luoyer.http.Constant;
import com.heimat.luoyer.ui.main.NetData;

import java.util.Map;

/**
 * Created by code5 on 2017/4/14.
 * 所有的网络请求都在这个类里面进行管理
 */
public class HttpManager {
    public static Request<NetData> getData(Map<String,Object> hashMap){
        return new PostRequest<NetData>(Constant.GET_DATA,NetData.class,hashMap);
    }
    public static Request<NetData> getList(Map<String,Object> hashMap){
        return new GetRequest<NetData>(Constant.GET_DATA,NetData.class);
    }
}
