package com.zj.pedometer.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.zj.pedometer.R;

/**
 * create by zj on 2018/11/27
 */
public class CircleBar extends View {

    //圆环宽度
    int circleWidth;
    //圆环view背景色
    int bgColor;
    //圆环默认颜色
    int circlreNormalColor;
    //圆环切换颜色
    int circleChangeColor;
    //字体默认颜色;
    int textNormalColor;
    int textNormalSize;
    int textChangeColor;
    int textChangeSize;
    private int stepSize;
    private Paint circlePaint;
    private Paint circleChangePaint;
    private Paint textPaint;
    private RectF circleRect;
    //完成时的值（比如默认100步，100步后完成任务circlebar完成）
    private int normalStepSize=100;
    public CircleBar(Context context) {
        this(context,null);
    }

    public CircleBar(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CircleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }


    public void init(Context context, @Nullable AttributeSet attrs){
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.CircleBar);
        circleWidth= (int) typedArray.getDimension(R.styleable.CircleBar_circleWidth,20);
        bgColor=typedArray.getColor(R.styleable.CircleBar_bgColor,0xffFFFFFF);
        circlreNormalColor=typedArray.getColor(R.styleable.CircleBar_circleNormalColor,0xff545454);
        circleChangeColor=typedArray.getColor(R.styleable.CircleBar_circleChangeColor,0xffff0000);
        textNormalColor=typedArray.getColor(R.styleable.CircleBar_textNormalColor,0xff545454);
        textChangeColor=typedArray.getColor(R.styleable.CircleBar_textChangeColor,0xffff0000);
        textNormalSize=typedArray.getInteger(R.styleable.CircleBar_textNormalSize,30);
        textChangeSize=typedArray.getInteger(R.styleable.CircleBar_textChangeSize,50);
        typedArray.recycle();

        circlePaint=new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(circlreNormalColor);
        circlePaint.setStrokeWidth(circleWidth);
        circlePaint.setTextSize(20);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeCap(Paint.Cap.ROUND);

        circleChangePaint=new Paint();
        circleChangePaint.setAntiAlias(true);
        circleChangePaint.setColor(circleChangeColor);
        circleChangePaint.setStrokeWidth(circleWidth);
        circleChangePaint.setTextSize(20);
        circleChangePaint.setStyle(Paint.Style.STROKE);
        circleChangePaint.setStrokeCap(Paint.Cap.ROUND);

        textPaint=new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(textNormalColor);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setTextSize(textNormalSize);
        textPaint.setStrokeWidth(1);

        circleRect=new RectF();

    }



    public void setData(int size){
        stepSize=size;
        invalidate();
    }

    private float viewWidth;
    private float viewHeight;
    private float radius;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        viewWidth=getMeasuredWidth();
        viewHeight=getMeasuredHeight();
        radius=(Math.min(viewWidth,viewHeight)-4*circleWidth)/2;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        circleRect.left=viewWidth/2-radius;
        circleRect.top=viewHeight/2-radius;
        circleRect.bottom=viewHeight/2+radius;
        circleRect.right=viewWidth/2+radius;
        canvas.drawOval(circleRect,circlePaint);
        canvas.drawText(""+stepSize,viewWidth/2,viewHeight/2,textPaint);

        if (stepSize>0){
            float angle=stepSize*360/normalStepSize;
            canvas.drawArc(circleRect,90,angle,false,circleChangePaint);
        }
    }
}
