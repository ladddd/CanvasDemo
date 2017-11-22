package com.ladddd.canvasdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 陈伟达 on 2017/11/17.
 */

public class CustomView extends View {

    private Paint mPaint;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mPaint == null) {
            mPaint = new Paint();
            mPaint.setColor(Color.BLUE);
            mPaint.setStyle(Paint.Style.STROKE);
            // STROKE WIDTH 0
            // Canvas 可以设置 Matrix 来实现几何变换（如放大、缩小、平移、旋转），在几何变换之后 Canvas 绘制的内容就会发生相应变化，
            // 包括线条也会加粗，例如 2 像素宽度的线条在 Canvas 放大 2 倍后会被以 4 像素宽度来绘制。而当线条宽度被设置为 0 时，
            // 它的宽度就被固定为 1 像素，就算 Canvas 通过几何变换被放大，它也依然会被以 1 像素宽度来绘制。
            mPaint.setStrokeWidth(20);
            //抗锯齿
            mPaint.setAntiAlias(true);
        }

        //在之前绘制的图层之上添加颜色遮罩层
//        canvas.drawColor(Color.YELLOW);
        //画圆，圆心坐标 半径
//        canvas.drawCircle(getMeasuredWidth()/2, getMeasuredHeight()/2, 500, mPaint);
//
//        mPaint.setStrokeWidth(80);
        //设置画笔笔触形状
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
        //设置拐角形状 ROUND：圆角 MITER 尖角 BEVEL 平角
        mPaint.setStrokeJoin(Paint.Join.MITER);
        mPaint.setStrokeMiter(30); // < 30 Butt
//
//        canvas.drawPoint(getMeasuredWidth()/2, getMeasuredHeight()/2, mPaint);

        //画线必须采用STROKE
//        mPaint.setStyle(Paint.Style.FILL);
//        canvas.drawArc(new RectF(100f, 100f, 500f, 500f), -30, -120, true, mPaint);
        //画圆弧，前四个参数是圆弧所在椭圆的四个坐标， 起始角度，终止角度，是否是扇形（连接圆心）
//        canvas.drawArc(0, 0, 400, 400, 30, 120, true, mPaint);

        Path path = new Path();
        path.moveTo(10, 0);
        path.lineTo(0, 14);
        path.lineTo(20, 14);
        path.lineTo(10, 0);

        Path p1 = new Path();
//        path.addArc(200, 200, 400, 400, -225, 225);
        p1.lineTo(400, 200);
        p1.lineTo(400, 542);
        p1.lineTo(500, 542);
        p1.lineTo(400, 542);
//        path.setFillType(Path.FillType.WINDING);  //根据重叠层数，奇数填充，偶数不填充原则
//        path.setFillType(Path.FillType.EVEN_ODD); //根据笔触的方向判定是否填充
//        //第四个参数 路径方向，处理相交区域
//        path.addCircle(500, 500, 300, Path.Direction.CW);
//        path.addCircle(700, 500, 300, Path.Direction.CW);
//


//        mPaint.setPathEffect(new DashPathEffect(new float[]{8, 4}, 8));
//        mPaint.setPathEffect(new CornerPathEffect(200));50, 10));
//        mPaint.setPathEffect(new DiscretePathEffect(
        mPaint.setPathEffect(new PathDashPathEffect(path, 40, 0, PathDashPathEffect.Style.MORPH));

//        path.lineTo(100, 100);
//        path.lineTo(100, 800);
//        //封闭图形，等同于lineTo(1000, 500) 当Paint为FILL或FILL_AND_STROKE时会自动封闭
//        path.close();
        canvas.drawPath(p1, mPaint);

//        mPaint.setTextSize(20);
//        mPaint.setColor(Color.RED);
//        canvas.drawText("hello world", 300, 300, mPaint);

    }
}
