package com.ladddd.canvasdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CameraRotateView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    int degree = 0;
    int oppositeDegree = 0;
    int pivot = 0;
    Camera camera = new Camera();

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
        invalidate();
    }

    public int getOppositeDegree() {
        return oppositeDegree;
    }

    public void setOppositeDegree(int oppositeDegree) {
        this.oppositeDegree = oppositeDegree;
        invalidate();
    }

    public int getPivot() {
        return pivot;
    }

    public void setPivot(int pivot) {
        this.pivot = pivot;
        invalidate();
    }

    public CameraRotateView(Context context) {
        super(context);
    }

    public CameraRotateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CameraRotateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.logo1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        float scaleX = (float) getWidth()/bitmapWidth*0.8f;
        float scaleY = (float) getHeight()/bitmapHeight*0.8f;
        int x = centerX - bitmapWidth / 2;
        int y = centerY - bitmapHeight / 2;

        //左
        canvas.save();
        camera.save();

        canvas.translate(centerX, centerY);
        canvas.rotate(pivot);

        camera.rotateY(oppositeDegree);
        camera.applyToCanvas(canvas);
        canvas.clipRect(-centerX, -centerY, 0, centerY);

        canvas.scale(scaleX, scaleY);
        canvas.rotate(-pivot);
        canvas.translate(-centerX, -centerY);
        canvas.drawBitmap(bitmap, x, y, paint);

        camera.restore();
        canvas.restore();

        //右
        canvas.save();
        camera.save();

        canvas.translate(centerX, centerY);
        canvas.rotate(pivot);

        camera.rotateY(degree);
        camera.applyToCanvas(canvas);
        canvas.clipRect(0,-centerY, centerX, centerY);

        canvas.scale(scaleX, scaleY);
        canvas.rotate(-pivot);
        canvas.translate(-centerX, -centerY);
        canvas.drawBitmap(bitmap, x, y, paint);

        camera.restore();
        canvas.restore();
    }
}