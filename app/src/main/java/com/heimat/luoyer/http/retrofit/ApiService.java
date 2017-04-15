package com.heimat.luoyer.http.retrofit;

import com.heimat.luoyer.base.BaseBean;
import com.heimat.luoyer.ui.zhihu.bean.NewsSummary;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by code5 on 2017/4/13.
 */
public interface ApiService {
    String API_SERVER_URL="";
    @GET("getService")
    Observable<BaseBean> getData();
    @POST("")
    Observable<BaseBean> getList(@QueryMap Map<String,Object> map);
    @GET("getService")
    Observable<BaseBean> getDatas(@Query("Name") String name,@Query("Age") int age);
    Observable <List<NewsSummary>> getNewsListData(String type, final String id, int startPage);

}
