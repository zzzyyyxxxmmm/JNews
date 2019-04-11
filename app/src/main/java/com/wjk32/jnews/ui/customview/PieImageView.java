package com.wjk32.jnews.ui.customview;

/**
 * Created by Jikang Wang on 4/11/19.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.IntRange;

@SuppressLint("AppCompatCustomView")
public class PieImageView extends ImageView{




    private final int MAX_PROGRESS = 100;
    private Paint mArcPaint;
    private Paint mCirclePaint;
    private int mProgress = 0;
    private final int length=120;


    RectF oval;
    public PieImageView(Context context) {
        this(context, null, 0);
        init();
    }

    public PieImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public PieImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setProgress(@IntRange(from = 0, to = MAX_PROGRESS) int mProgress) {
        this.mProgress = mProgress;

        invalidate();
        //ViewCompat.postInvalidateOnAnimation(this);
    }

    private void init() {
        mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mArcPaint.setStrokeWidth(AnimateUtils.dpToPixel(0.1f, getContext()));
        mArcPaint.setColor(Color.argb(120, 0xff, 0xff, 0xff));

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(AnimateUtils.dpToPixel(2, getContext()));
        mCirclePaint.setColor(Color.argb(120, 0xff, 0xff, 0xff));


        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        float x = display.getWidth()/2-length/2;
        float y = display.getHeight()/2-length/2;



        oval = new RectF( x, y,
                x+length, y+length);



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mProgress != MAX_PROGRESS) {
            float mAngle = mProgress * 360f / MAX_PROGRESS;
            canvas.drawArc(oval, 270, mAngle, true, mArcPaint);
            canvas.drawCircle(oval.centerX(), oval.centerY(), length/2, mCirclePaint);
        }

    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}