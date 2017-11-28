package com.ladddd.canvasdemo.animate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 陈伟达 on 2017/11/28.
 */

public class SportsView extends View {

    private Paint mPaint;
    private RectF arcRect;
    //ObjectAnimation 需要set get方法
    private float progress = 0;

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    public SportsView(Context context) {
        super(context);
        init();
    }

    public SportsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SportsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        arcRect = new RectF(10, 10, 300, 300);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        arcRect.right = getMeasuredWidth() - 10;
        arcRect.bottom = getMeasuredHeight() - 10;
        canvas.drawArc(arcRect, 135, progress * 2.7f, false, mPaint);
    }
}
