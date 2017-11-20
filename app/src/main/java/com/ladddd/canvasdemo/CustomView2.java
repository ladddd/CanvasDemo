package com.ladddd.canvasdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.ComposeShader;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 陈伟达 on 2017/11/20.
 */

public class CustomView2 extends View {

    private Paint mPaint;
    private Bitmap bitmap;
    private Bitmap bitmap2;
    private Shader mShader;
    private Shader mShader2;
    private Shader mShader3;
    private Xfermode xfermode;

    public CustomView2(Context context) {
        super(context);

        init();
    }

    public CustomView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {

        //线性渐变
        //渐变端点位置 填充模式 CLAMP: 起始位置以前用起始颜色填充，结束位置以后用结束颜色填充。
//        mShader = new LinearGradient(150f, 150f, 250f, 250f,
//                Color.parseColor("#E91E63"), Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
//
//        mShader2 = new LinearGradient(150f, 150f, 250f, 250f,
//                Color.parseColor("#E91E63"), Color.parseColor("#2196F3"), Shader.TileMode.MIRROR);
//
//        mShader3 = new LinearGradient(150f, 150f, 250f, 250f,
//                Color.parseColor("#E91E63"), Color.parseColor("#2196F3"), Shader.TileMode.REPEAT);

        //辐射渐变
//        mShader = new RadialGradient(200f, 200f, 200,
//                Color.parseColor("#E91E63"), Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);

//        mShader = new SweepGradient(100f, 200f,
//                Color.parseColor("#E91E63"), Color.parseColor("#2196F3"));

        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test);
        mShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP); //水平填充模式 垂直填充模式

        bitmap2 = BitmapFactory.decodeResource(getResources(), R.mipmap.logo);
        mShader2 = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        //复合shader

        //需要关闭硬件加速
        mShader3 = new ComposeShader(mShader, mShader2, PorterDuff.Mode.DST_IN);

        xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //颜色
        if (mPaint == null) {
            mPaint = new Paint();
            //1.设置颜色
            mPaint.setColor(Color.BLUE);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setStrokeWidth(20);
            //抗锯齿
            mPaint.setAntiAlias(true);
        }

//        mPaint.setColor(Color.GREEN);
//        canvas.drawPoint(90, 90, mPaint);
//        canvas.drawPoint(120, 120, mPaint);
//
//        //设置shader后 setColor就无效了
        //2. shader图层效果
        mPaint.setShader(mShader3);
//
//        mPaint.setShader(mShader2);
//        canvas.drawCircle(200, 600, 200, mPaint);
//
//        mPaint.setShader(mShader3);
//        canvas.drawCircle(200, 1000, 200, mPaint);

        //3 colorfiler 滤镜效果
        //R' = R * mul.R / 0xff + add.R
        //G' = G * mul.G / 0xff + add.G
        //B' = B * mul.B / 0xff + add.B
//        ColorFilter colorFilter1 = new LightingColorFilter(0x00ffff, 0x000000);
//        ColorFilter colorFilter1 = new PorterDuffColorFilter(Color.parseColor("#9900ffff"), PorterDuff.Mode.SRC_OVER);

        //R’ = a*R + b*G + c*B + d*A + e;
        //G’ = f*R + g*G + h*B + i*A + j;
        //B’ = k*R + l*G + m*B + n*A + o;
        //A’ = p*R + q*G + r*B + s*A + t;
//        ColorMatrix matrix = new ColorMatrix(new float[20]);
//        ColorFilter colorFilter1 = new ColorMatrixColorFilter(matrix);
//
//        mPaint.setColorFilter(colorFilter1);

//        canvas.drawCircle(400, 400, 400, mPaint);

        //xfermode左右和shader都是图层的叠加，xfermode无需禁用硬件加速
//        setLayerType(LAYER_TYPE_HARDWARE, mPaint); //---> 硬件缓存图层
        setLayerType(LAYER_TYPE_SOFTWARE, mPaint); //---> bitmap缓存
        int saved = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG); //保存之前的图层

        canvas.drawBitmap(bitmap, 0, 0, mPaint);
        mPaint.setXfermode(xfermode); //bitmap2背景是透明的，但是叠加时会变成黑色
        canvas.drawBitmap(bitmap2, 0, 0, mPaint);
        mPaint.setXfermode(null);

        canvas.restoreToCount(saved);
    }
}
