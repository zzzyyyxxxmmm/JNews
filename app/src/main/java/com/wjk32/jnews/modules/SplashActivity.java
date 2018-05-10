package com.wjk32.jnews.modules;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wjk32.jnews.base.BaseActivity;
import com.wjk32.jnews.modules.mainindex.MainActivity;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Observable.just(initialdata())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> MainActivity());

    }

    private void MainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public String initialdata(){
        return "";
    }
}
