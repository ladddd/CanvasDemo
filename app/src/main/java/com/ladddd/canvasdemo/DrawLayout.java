package com.ladddd.canvasdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by 陈伟达 on 2017/11/22.
 */

public class DrawLayout extends RelativeLayout {

    public DrawLayout(Context context) {
        super(context);
    }

    public DrawLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //绘制流程
    //1 背景 drawBackground 无法重载
    //2 主体 onDraw()  viewgroup会绕过这步，若要执行完整流程View.setWillNotDraw(false)
    //3 子 View dispatchDraw()
    //4 滑动边缘渐变和滑动条 onDrawForeground()
    //5 前景 onDrawForeground()
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    //字view的绘制会覆盖viewgroup的onDraw，绘制的覆盖物在此方法实现
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}
