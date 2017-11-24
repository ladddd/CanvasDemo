package com.ladddd.canvasdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;

/**
 * Created by 陈伟达 on 2017/11/23.
 */

public class ParallelImageView extends AppCompatImageView {

    private float offsetY = 0; //最大偏倚距离为height的10%
    private RecyclerInfoDelegate delegate;
    private Matrix baseMatrix;
    private Matrix updateMatrix;

    public ParallelImageView(Context context) {
        super(context);
        init();
    }

    public ParallelImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        baseMatrix = new Matrix();
        setScaleType(ScaleType.MATRIX);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Drawable drawable = getDrawable();
                int width = getWidth();
                int height = getHeight();
                int drawableWidth = drawable.getIntrinsicWidth();
                int drawbaleHeight = drawable.getIntrinsicHeight();
                baseMatrix.reset();
                float widthScale = (float) width / drawableWidth;
                baseMatrix.postScale(widthScale, widthScale);
                baseMatrix.postTranslate(0, (height - drawbaleHeight * widthScale)/2);

                setImageMatrix(baseMatrix);
            }
        });

        getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (delegate == null) {
                    return;
                }
                int[] windowPosition =  new int[2];
                getLocationInWindow(windowPosition);
                int y = windowPosition[1];
                int centerY = delegate.getLocation().centerY();
                int viewCenterY = y + getMeasuredHeight()/2;
                int range = delegate.getLocation().height() + getMeasuredHeight();
                int maxOffset = getMeasuredHeight()/3;
                offsetY = (float) maxOffset / range * (viewCenterY - centerY);

                updateMatrix = new Matrix();
                updateMatrix.reset();
                updateMatrix.setTranslate(0, offsetY);

                Matrix resultMatrix = new Matrix();
                resultMatrix.set(baseMatrix);
                resultMatrix.postConcat(updateMatrix);

                setImageMatrix(resultMatrix);
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(0, offsetY);
        super.onDraw(canvas);
        canvas.restore();
    }

    public void setRecyclerInfoDelegate(RecyclerInfoDelegate delegate) {
        this.delegate = delegate;
    }

    public interface RecyclerInfoDelegate {
        Rect getLocation();
    }
}
