package com.wjk32.jnews.modules.second;

import com.wjk32.jnews.entity.ImageEntity;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImageInterface {
    @GET("api/")
    Observable<ImageEntity> getImages(@Query("key") String apiKey,@Query("q") String query,@Query("image_type") String imagetype);


}
