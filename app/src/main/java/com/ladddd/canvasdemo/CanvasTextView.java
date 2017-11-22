package com.ladddd.canvasdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

/**
 * Created by 陈伟达 on 2017/11/21.
 */

public class CanvasTextView extends View {

    private Paint mPaint;

    public CanvasTextView(Context context) {
        super(context);
        init();
    }

    public CanvasTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
//        mPaint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setTextSize(100);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mPaint.setStrikeThruText(true);
        mPaint.setUnderlineText(true);
        mPaint.setTextSkewX(-0.2f);
        mPaint.setLetterSpacing(0.2f);
        mPaint.setLinearText(false);
//        mPaint.setTextAlign(Paint.Align.RIGHT);
//        mPaint.setTextScaleX(2.0f);
//        mPaint.setFakeBoldText(true);
//        mPaint.setPathEffect(new CornerPathEffect(200));

        Path path = new Path();
        path.moveTo(100, 100);
        path.lineTo(200, 400);
        path.arcTo(200, 350, 300, 450, 180, -180, false); //角度计算都是顺时针方向的
        path.lineTo(600, 200);

//        canvas.drawPath(path, mPaint);
        mPaint.setStyle(Paint.Style.FILL);
//        canvas.drawTextOnPath("hello world", path, 0, -10, mPaint);
//        canvas.drawText("hello world", 100, 200, mPaint);

        //文字换行 StaticLayout
//        mPaint.setTextSize(40);
//        TextPaint textPaint = new TextPaint(mPaint);
//        String text1 = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.";
//        StaticLayout staticLayout1 = new StaticLayout(text1, textPaint, 600,
//                Layout.Alignment.ALIGN_NORMAL, 1, 0, true);
//        String text2 = "a\nbc\ndefghi\njklm\nnopqrst\nuvwx\nyz";
//        StaticLayout staticLayout2 = new StaticLayout(text2, textPaint, 600,
//                Layout.Alignment.ALIGN_NORMAL, 1, 0, true);
//
//        canvas.save();
//        canvas.translate(50, 100);
//        staticLayout1.draw(canvas);
//        canvas.translate(0, 200);
//        staticLayout2.draw(canvas);
//        canvas.translate(0, 400);
//        canvas.drawText("a\nbc\ndefghi\njklm\nnopqrst\nuvwx\nyz", 0, 100, mPaint); //drawText文本中的\n会被空格替换，没有换行效果
//        canvas.restore();

        //获取

        float top = mPaint.getFontMetrics().top;
        float ascent = mPaint.getFontMetrics().ascent;
        float leading = mPaint.getFontMetrics().leading;
        float descent = mPaint.getFontMetrics().descent;
        float bottom = mPaint.getFontMetrics().bottom;

        float abseline = bottom - top + leading;

        mPaint.getFontSpacing();
        Rect rect = new Rect();
        mPaint.getTextBounds("hello world", 0, 11, rect); //第二个参数是length，不是endIndex
        mPaint.setStyle(Paint.Style.FILL);
//        canvas.translate(100, 200);
//        canvas.drawRect(rect, mPaint);
//        canvas.restore();

        float width = mPaint.measureText("hello world");
//        Toast.makeText(getContext(), "measureText: " + width + "\nboundWidth: " + (rect.right-rect.left), Toast.LENGTH_SHORT).show();

        float[] measuredWidth = {0};
        mPaint.breakText("hello world", 0, 11, true, 11, measuredWidth);
//        Toast.makeText(getContext(), "measureText: " + width + "\nbreakText: " + (rect.right-rect.left), Toast.LENGTH_SHORT).show();

        String text = "hello world";
        int length = text.length();
        float advance = mPaint.getRunAdvance(text, 0, length, 0, length, false, length-1);
        canvas.drawText(text, 100, 100, mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(100 + advance, 0, 100 + advance, 100, mPaint);

        mPaint.hasGlyph("a"); //true 是否只包含单个“字形”,可以是多个字符对应一个字形，unicode
    }
}
