package com.ladddd.canvasdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 陈伟达 on 2017/11/22.
 */

public class CanvasClipView extends View {

    private Paint mPaint;
    private Bitmap bitmap1;

    public CanvasClipView(Context context) {
        super(context);
        init();
    }

    public CanvasClipView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);

        bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.test);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        Rect rect = new Rect(200, 200, 300, 300);
//        canvas.clipRect(rect);
//        canvas.drawBitmap(bitmap1, 100, 100, mPaint);
//        canvas.restore();
////        canvas.rotate(45, 100, 100);
////        canvas.scale(1.3f, 0.6f);
//        canvas.skew(-0.5f, 0);
//        canvas.drawBitmap(bitmap1, 300, 100, mPaint);

        //canvas的顺序与代码顺序相反
        Matrix matrix = new Matrix();
        //ltrb 四角坐标 {l t r t l b r b}
        float[] pointsSrc = {200, 200, 200+bitmap1.getWidth(), 200, 200, 200+bitmap1.getHeight(), 200+bitmap1.getWidth(), 200+bitmap1.getHeight()}; //xy对
        float[] pointsDst = {190, 250, 320+bitmap1.getWidth(), 110, 220, 230+bitmap1.getHeight(), 230+bitmap1.getWidth(), 260+bitmap1.getHeight()};
        matrix.reset();
        matrix.setPolyToPoly(pointsSrc, 0, pointsDst, 0, 4);
//
//        canvas.setMatrix(matrix);
//        canvas.drawBitmap(bitmap1, 200, 200, mPaint);

        //Camera
        Camera camera = new Camera();
        canvas.drawBitmap(bitmap1, 200, 200, mPaint);

        canvas.save();
        camera.save();
        camera.rotateX(30);
        canvas.translate(bitmap1.getWidth(), bitmap1.getHeight());

        camera.applyToCanvas(canvas);
        canvas.translate(-bitmap1.getWidth(), -bitmap1.getHeight());
        camera.restore();

        canvas.drawBitmap(bitmap1, 200, 200, mPaint);
        canvas.restore();
    }
}
