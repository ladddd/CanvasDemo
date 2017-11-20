package com.ladddd.canvasdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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
//
//        canvas.drawPoint(getMeasuredWidth()/2, getMeasuredHeight()/2, mPaint);

        //画线必须采用STROKE
        mPaint.setStyle(Paint.Style.FILL);
//        canvas.drawArc(new RectF(100f, 100f, 500f, 500f), -30, -120, true, mPaint);
        //画圆弧，前四个参数是圆弧所在椭圆的四个坐标， 起始角度，终止角度，是否是扇形（连接圆心）
//        canvas.drawArc(0, 0, 400, 400, 30, 120, true, mPaint);

        Path path = new Path();
//        path.addArc(200, 200, 400, 400, -225, 225);
//        path.arcTo(400, 200, 600, 400, -180, 225, false);
//        path.lineTo(400, 542);
        path.setFillType(Path.FillType.WINDING);  //根据重叠层数，奇数填充，偶数不填充原则
        path.setFillType(Path.FillType.EVEN_ODD); //根据笔触的方向判定是否填充
        //第四个参数 路径方向，处理相交区域
        path.addCircle(500, 500, 300, Path.Direction.CW);
        path.addCircle(700, 500, 300, Path.Direction.CW);
//
//        path.lineTo(100, 100);
//        path.lineTo(100, 800);
//        //封闭图形，等同于lineTo(1000, 500) 当Paint为FILL或FILL_AND_STROKE时会自动封闭
//        path.close();
        canvas.drawPath(path, mPaint);

        mPaint.setTextSize(20);
        mPaint.setColor(Color.RED);
        canvas.drawText("hello world", 300, 300, mPaint);

    }
}
