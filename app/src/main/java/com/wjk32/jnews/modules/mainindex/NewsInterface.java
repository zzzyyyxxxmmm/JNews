package com.wjk32.jnews.modules.mainindex;

import com.wjk32.jnews.entity.News;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wjk32 on 12/29/2017.
 */

public interface NewsInterface {
    @GET("top-headlines")
    Observable<News> getNews(@Query("sources") String sources, @Query("apiKey") String apiKey);
}
