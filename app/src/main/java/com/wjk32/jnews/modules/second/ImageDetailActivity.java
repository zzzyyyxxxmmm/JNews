package com.wjk32.jnews.modules.second;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.wjk32.jnews.R;
import com.wjk32.jnews.base.BaseActivity;
import com.wjk32.jnews.entity.Artical;
import com.wjk32.jnews.entity.ImageEntity;
import com.wjk32.jnews.modules.Constants;
import com.wjk32.jnews.modules.mainindex.NewsDetail.NewsDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageDetailActivity extends BaseActivity {

    private static final String TRANSLATE_VIEW = "translate_view";
    ImageEntity.HitsBean hitsBean;

    @BindView(R.id.expanded_image)
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.hitsBean=(ImageEntity.HitsBean)getIntent().getSerializableExtra(Constants.TONEWSDETAILS);
        setContentView(R.layout.tab_bar_02_imagedetail);
        ButterKnife.bind(this);
        Picasso.with(this).load(hitsBean.getPreviewURL()).into(imageView);

        Picasso.with(this).load(hitsBean.getLargeImageURL()) .placeholder(imageView.getDrawable()).into(imageView);
    }

    public static void start(Context context, View view, ImageEntity.HitsBean hitsBean) {
        Intent starter = new Intent(context, ImageDetailActivity.class);
        starter.putExtra(Constants.TONEWSDETAILS, hitsBean);
        ActivityCompat.startActivity((Activity) context, starter, ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, view, TRANSLATE_VIEW).toBundle());
    }
}
