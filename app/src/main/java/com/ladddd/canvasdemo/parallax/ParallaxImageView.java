package com.ladddd.canvasdemo.parallax;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by 陈伟达 on 2017/11/24.
 */

public class ParallaxImageView extends AppCompatImageView {

    private final float DEFAULT_PARALLAX_RATIO = 1.2f;
    private float parallaxRatio = DEFAULT_PARALLAX_RATIO;

    private boolean needToTranslate = false;
    private TranslationInfo mTranslationInfo;

    private Matrix resultMatrix = new Matrix();

    private ParallaxImageListener listener;

    public interface ParallaxImageListener {
        TranslationInfo requireValuesForTranslate();
    }

    public ParallaxImageView(Context context) {
        super(context);
    }

    public ParallaxImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParallaxImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void requestTranslate() {
        if (needToTranslate) {
            needToTranslate = !doTranslate();
        }
    }

    public boolean doTranslate() {
        if (getDrawable() == null) {
            return false;
        }

        if (getListener() != null && getValues()) {
            calculateAndMove();
            return true;
        } else {
            return false;
        }
    }

    public void calculateAndMove() {
        int y = mTranslationInfo.getItemTop();
        int centerY = (mTranslationInfo.getRecyclerViewTop() + mTranslationInfo.getRecyclerViewHeight())/2;
        int viewCenterY = y + getMeasuredHeight()/2;
        int range = mTranslationInfo.getRecyclerViewHeight() + getMeasuredHeight();
        int maxOffset = getMeasuredHeight()/3;
        float offsetY = (float) maxOffset / range * (viewCenterY - centerY);

        resultMatrix.reset();
        float widthScale = (float) getMeasuredWidth() / getDrawable().getIntrinsicWidth();
        resultMatrix.setScale(widthScale, widthScale);
        resultMatrix.postTranslate(0, (getMeasuredHeight() - getDrawable().getIntrinsicHeight() * widthScale)/2);
        resultMatrix.postTranslate(0, offsetY);

        setImageMatrix(resultMatrix);
        invalidate();
    }

    public boolean getValues() {
        mTranslationInfo = getListener().requireValuesForTranslate();
        return null != mTranslationInfo;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        requestTranslate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        requestTranslate();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        requestTranslate();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        requestTranslate();
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        requestTranslate();
    }

    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        requestTranslate();
    }

    public void setListener(ParallaxImageListener listener) {
        this.listener = listener;
    }

    public ParallaxImageListener getListener() {
        return listener;
    }
}
