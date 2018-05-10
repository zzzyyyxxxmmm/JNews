package com.wjk32.jnews.modules.mainindex;

import android.annotation.SuppressLint;

import com.wjk32.jnews.R;
import com.wjk32.jnews.constants.NewsC;
import com.wjk32.jnews.entity.Artical;
import com.wjk32.jnews.entity.News;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.wjk32.jnews.constants.NewsC.APIKEY;
import static com.wjk32.jnews.constants.NewsC.SOURCES;

public class NewsPresenter implements NewsContract.Presenter {

    private final NewsContract.View mNewsView;

    ArrayList<String> stringlist= new ArrayList<>();
    ArrayList<Artical> articalList;


    private int mHeaderNums;
    private int mCurrentHeader;
    Disposable disposable;
    public NewsPresenter(NewsContract.View mNewsView) {
        this.mNewsView = mNewsView;
        this.mCurrentHeader=0;
    }


    @Override
    public void loadNews() {
        okhttp3.OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(NewsC.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        NewsInterface apiService = retrofit.create(NewsInterface.class);
        Observable<News> observable = apiService.getNews(SOURCES,APIKEY);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<News>() {
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
                    public void onNext(News newsList) {
                        articalList=new ArrayList<Artical>();
                        articalList=newsList.getArticlest();
                        articalList.addAll(newsList.getArticlest());
                        mHeaderNums=articalList.size();
                        mNewsView.showNews(articalList);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void changeNewsheader(boolean stop,int currentpage) {
        if(currentpage!=mCurrentHeader){
            mCurrentHeader=currentpage;
        }
        if(stop){
            Observable observable= Observable.interval(3,TimeUnit.SECONDS);
            disposable= observable.subscribe(
                    stats-> {
                        if(mCurrentHeader>=mHeaderNums) mCurrentHeader=0;
                        mNewsView.showHeader(mCurrentHeader++);
                    },
                    throwable -> {

                    }
            );
        }
        else{
            if(disposable!=null&&!disposable.isDisposed()){
                disposable.dispose();
            }
        }

    }

    @Override
    public void start() {
        loadNews();
    }


}
