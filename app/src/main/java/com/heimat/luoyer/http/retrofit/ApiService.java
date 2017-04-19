package com.heimat.luoyer.http.retrofit;

import com.heimat.luoyer.ui.zhihu.bean.News;
import com.heimat.luoyer.ui.zhihu.bean.ResultResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by code5 on 2017/4/13.
 */
public interface ApiService {
    String HOST = "http://www.toutiao.com/";
    String API_SERVER_URL = HOST + "api/";

    String URL_ARTICLE_FEED = "article/feed/";
    String URL_COMMENT_LIST = "comment/list/";
    String HOST_VIDEO = "http://i.snssdk.com";
    String URL_VIDEO="/video/urls/v/1/toutiao/mp4/%s?r=%s";

    /**
     * 获取新闻数据列表
     */
    @GET(URL_ARTICLE_FEED + "?utm_source=toutiao&max_behot_time_tmp=0&as=A1C528E25E76FB8&cp=582EC64FEBD84E1&max_behot_time=0")
    Observable<ResultResponse<List<News>>> getNews(@Query("category") String category, @Query("widen") int PageIndex);


}
