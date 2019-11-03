package com.dh.summarize.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author 86351
 * @date 2019/10/7
 * @description
 */
public class CanvasView extends View {
    private Paint mPaint;
    private int mWidth, mHeght;

    public CanvasView(Context context) {
        this(context, null);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeght = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 平移操作
        /*canvas.drawCircle(200, 200, 150, mPaint);
        // 画布的起始点会移动到(200, 200)做个坐标点
        canvas.translate(200,200);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(200, 200, 150, mPaint);*/

        // 缩放
        /*canvas.drawRect(0, 0, 400, 400, mPaint);
        // 将画布缩放到原来一半
        //canvas.scale(0.5f, 0.5f);

        // 先平移(px,py)，然后缩放scale(sx,sy)，再反向平移(-px,-py)
        // 也可以看成以(200,200)这个点进行缩放
        canvas.scale(0.5f,0.5f,200,200);
        //canvas.translate(200, 200);
        //canvas.scale(0.5f, 0.5f);
        //canvas.translate(-200, -200);

        mPaint.setColor(Color.RED);
        canvas.drawRect(0, 0, 400, 400, mPaint);*/

        // 为了使效果明显，将坐标点圆点移动到画布中心
        /*canvas.translate(mWidth / 2, mHeght / 2);
        canvas.drawRect(0, 0, 400, 400, mPaint);
        //canvas.scale(-0.5f, -0.5f);
        canvas.scale(-0.5f, -0.5f, 200, 0);
        //canvas.translate(200, 0);
        //canvas.scale(-0.5f, -0.5f);
        //canvas.translate(-200, 0);

        mPaint.setColor(Color.RED);
        canvas.drawRect(0, 0, 400, 400, mPaint);*/


        // 旋转
        /*canvas.translate(mWidth / 2, mHeght / 2);
        canvas.drawRect(0, 0, 400, 400, mPaint);

        // 顺时针旋转50度
        //canvas.rotate(50);
        // 设置顺时针旋转50度，旋转点(200, 200)
        canvas.rotate(50,200,200);

        mPaint.setColor(Color.RED);
        canvas.drawRect(0, 0, 400, 400, mPaint);*/


        // 错切
        /*canvas.translate(mWidth / 2 - 200, mHeght / 2 - 200);
        canvas.drawRect(0, 0, 400, 400, mPaint);

        // sx，sy就是三角函数中的tan值
        //在X方向倾斜45度,Y轴逆时针旋转45
        //canvas.skew(1, 0);
        // 在Y轴方向倾斜45度，X轴顺时针旋转45度
        //canvas.skew(0,1);
        canvas.skew(0.5f,0.5f);

        mPaint.setColor(Color.RED);
        canvas.drawRect(0, 0, 400, 400, mPaint);*/


        // 切割
        /*canvas.translate(mWidth / 2 - 200, mHeght / 2 - 200);
        canvas.drawRect(0, 0, 400, 400, mPaint);

        // 裁剪画布
        // canvas.clipRect(200, 200, 600, 600);
        canvas.clipRect(200, 200, 600, 600);
        *//*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            canvas.clipOutRect(200f,200f,600f,600f);
        }*//*

        mPaint.setColor(Color.RED);
        // 超出画布区域的部分不能被绘制
        canvas.drawRect(0, 0, 300, 300, mPaint);
        mPaint.setColor(Color.MAGENTA);
        // 在画布范围内可以被绘制
        canvas.drawRect(200, 200, 500, 500, mPaint);*/


        // 矩阵操作canvas的变换
        //canvas.translate(mWidth / 2 - 200, mHeght / 2 - 200);
        canvas.drawRect(0, 0, 400, 400, mPaint);

        // 使用矩阵
        Matrix matrix = new Matrix();
        // 使用矩阵提供的平移方法
        matrix.setTranslate(200, 200);
        matrix.setRotate(45);
        matrix.setRotate(45, 0, 0);
        matrix.setScale(0.5f, 0.5f);
        matrix.setScale(0.5f, 0.5f, 100, 0);
        matrix.setSkew(1,0);
        matrix.setSkew(0,1);
        canvas.setMatrix(matrix);

        mPaint.setColor(Color.RED);
        canvas.drawRect(0, 0, 400, 400, mPaint);
    }
}
