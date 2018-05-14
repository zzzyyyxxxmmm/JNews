package com.wjk32.jnews.modules.second;

import com.wjk32.jnews.constants.ImageC;
import com.wjk32.jnews.constants.NewsC;
import com.wjk32.jnews.entity.Artical;
import com.wjk32.jnews.entity.ImageEntity;
import com.wjk32.jnews.entity.News;
import com.wjk32.jnews.modules.mainindex.NewsContract;
import com.wjk32.jnews.modules.mainindex.NewsInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.wjk32.jnews.constants.NewsC.APIKEY;
import static com.wjk32.jnews.constants.NewsC.SOURCES;

public class ImagePresenter implements ImageContract.Presenter {

    public List<ImageEntity.HitsBean> imageList;

    private final ImageContract.View mImageView;


    Disposable disposable;
    public ImagePresenter(ImageContract.View mImageView) {
        this.mImageView = mImageView;
    }

    @Override
    public void loadImage(boolean refresh) {
        okhttp3.OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ImageC.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ImageInterface apiService = retrofit.create(ImageInterface.class);
        Observable<ImageEntity> observable = apiService.getImages(ImageC.APIKEY,ImageC.Q,ImageC.IMAGE_TYPE);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImageEntity>() {
                    @Override
                    public void onComplete() {
                    }
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ImageEntity imageEntity) {
                        imageList=new ArrayList<ImageEntity.HitsBean>();
                        imageList.addAll(imageEntity.getHits());
                        mImageView.showImage(imageList,refresh);
                    }
                });
    }

    @Override
    public void undispose() {

    }

    @Override
    public void start(boolean refresh) {

    }
}
