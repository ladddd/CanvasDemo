package com.ladddd.canvasdemo.animate;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.ladddd.canvasdemo.R;

/**
 * Created by 陈伟达 on 2017/11/28.
 */

public class FlipView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;

    public FlipView(Context context) {
        super(context);
        init();
    }

    public FlipView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FlipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.logo1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
    }
}
