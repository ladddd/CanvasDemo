package com.ladddd.canvasdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 陈伟达 on 2017/11/23.
 */

public class LissajousView extends View {

    private Paint mPaint;
    private Path mPath;
    // x = sin(9θ)
    // y = sin(8θ)
    private float x;
    private float y;
    private float θ = 0;
    private Handler mHandler;

    public LissajousView(Context context) {
        super(context);
        init();
    }

    public LissajousView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LissajousView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPath = new Path();
        x = (float) (300 * Math.sin(9*θ));
        y = (float) (300 * Math.sin(8*θ));
        mPath.moveTo(x, y);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);

        mHandler = new Handler();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                θ += Math.PI/1000;
                x = (float) (300 * Math.sin(9*θ));
                y = (float) (300 * Math.sin(8*θ));
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        invalidate();
                    }
                });
            }
        }, 0, 10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.translate(getWidth()/2, getHeight()/2);
        mPath.lineTo(x, y);
        canvas.drawPath(mPath, mPaint);
        canvas.restore();
    }
}
