package com.wjk32.jnews.modules.mainindex.NewsDetail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.widget.NestedScrollView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.wjk32.jnews.R;
import com.wjk32.jnews.base.BaseActivity;
import com.wjk32.jnews.entity.Artical;
import com.wjk32.jnews.modules.Constants;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailActivity extends BaseActivity implements NewsDetailContract.View{

    private static final String TRANSLATE_VIEW = "translate_view";
    @BindView(R.id.detail_bar_image)
    ImageView detailBarImage;
    @BindView(R.id.detail_bar_copyright)
    TextView detailBarCopyright;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.wv_detail_content)
    WebView wvDetailContent;
    @BindView(R.id.nsv_scroller)
    NestedScrollView nsvScroller;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    boolean isTransitionEnd = false;
    boolean isImageShow = false;
    String imgUrl;
    Artical artical;

    @Nullable
    @Override
    public Intent getSupportParentActivityIntent() {
        onBackPressed();
        return null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_bar_01_newsdetail);
        ButterKnife.bind(this);
        // my_child_toolbar is defined in the layout file
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);


        artical=(Artical) getIntent().getSerializableExtra(Constants.TONEWSDETAILS);
        collapsingToolbar.setTitle(artical.getTitle());
        Picasso.with(getBaseContext()).load(artical.getUrlToImage()).into(detailBarImage);
        WebSettings settings = wvDetailContent.getSettings();
        settings.setBlockNetworkImage(true);
        wvDetailContent.loadUrl(artical.getUrl());
        wvDetailContent.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });

    }


    public static void start(Context context, View view, Artical artical) {
        Intent starter = new Intent(context, NewsDetailActivity.class);
        starter.putExtra(Constants.TONEWSDETAILS, artical);
        ActivityCompat.startActivity((Activity) context, starter, ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, view, TRANSLATE_VIEW).toBundle());
    }

    @Override
    public void showNews(List<Artical> articalList) {

    }

    @Override
    public void showHeader(int position) {

    }

    @Override
    public void setPresenter(NewsDetailContract.Presenter presenter) {

    }
}
