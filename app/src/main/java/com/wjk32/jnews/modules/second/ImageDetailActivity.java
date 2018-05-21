package com.wjk32.jnews.modules.second;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.wjk32.jnews.R;
import com.wjk32.jnews.base.BaseActivity;
import com.wjk32.jnews.entity.Artical;
import com.wjk32.jnews.entity.ImageEntity;
import com.wjk32.jnews.modules.Constants;
import com.wjk32.jnews.modules.mainindex.NewsDetail.NewsDetailActivity;
import com.wjk32.jnews.util.PicassoUtil;
import com.wjk32.library.view.PieImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageDetailActivity extends BaseActivity{

    private static final String TRANSLATE_VIEW = "translate_view";
    ImageEntity.HitsBean hitsBean;

    @BindView(R.id.expanded_image)
    PieImageView imageView;

    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;
    long startTime=0;
    long endTime=0;
    boolean isclick=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.hitsBean=(ImageEntity.HitsBean)getIntent().getSerializableExtra(Constants.TONEWSDETAILS);
        setContentView(R.layout.tab_bar_02_imagedetail);
        ButterKnife.bind(this);

        Picasso.with(this).load(hitsBean.getPreviewURL()).into(imageView);


        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("onTouch");

                int action=event.getAction();

                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("ACTION_DOWN");
                        startTime = System.currentTimeMillis();
                        isclick=false;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mScaleGestureDetector.onTouchEvent(event);
                        isclick=false;
                        break;
                    case MotionEvent.ACTION_UP:
                        System.out.println("UP");
                        endTime = System.currentTimeMillis();
                        //当从点击到弹起小于半秒的时候,则判断为点击,如果超过则不响应点击事件
                        if ((endTime - startTime) > 0.1 * 1000L) {
                            isclick = true;
                        } else {
                            isclick = false;
                        }
                }
                System.out.println("isclick="+isclick);
                return isclick;
            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        imageView.setProgress((int)(0));
        PicassoUtil.ProgressListener progressListener = new PicassoUtil.ProgressListener() {
            boolean firstUpdate = true;

            @Override public void update(long bytesRead, long contentLength, boolean done) {
                if (done) {
                    System.out.println("completed");
                } else {
                    if (firstUpdate) {
                        firstUpdate = false;
                        if (contentLength == -1) {
                            System.out.println("content-length: unknown");
                        } else {
                            System.out.format("content-length: %d\n", contentLength);
                        }
                    }

                    System.out.println(bytesRead);

                    if (contentLength != -1) {
                        System.out.format("%d%% done\n", (100 * bytesRead) / contentLength);
                        imageView.setProgress((int)((100 * bytesRead) / contentLength));
                    }
                }
            }
        };

        PicassoUtil.getPicasso(this,progressListener).load(hitsBean.getLargeImageURL()) .placeholder(imageView.getDrawable()).into(imageView);

        //Picasso.with(this).load(hitsBean.getLargeImageURL()) .placeholder(imageView.getDrawable()).into(imageView);

        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
    }

    public static void start(Context context, View view, ImageEntity.HitsBean hitsBean) {
        Intent starter = new Intent(context, ImageDetailActivity.class);
        starter.putExtra(Constants.TONEWSDETAILS, hitsBean);
        //ActivityCompat.startActivity((Activity) context, starter, ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, view, TRANSLATE_VIEW).toBundle());

    }





    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector){
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f,
                    Math.min(mScaleFactor, 10.0f));
            imageView.setScaleX(mScaleFactor);
            imageView.setScaleY(mScaleFactor);
            return true;
        }
    }
}
