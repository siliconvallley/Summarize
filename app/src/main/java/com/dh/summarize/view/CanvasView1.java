package com.dh.summarize.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author 86351
 * @date 2019/10/7
 * @description
 */
public class CanvasView1 extends View {
    private Paint mPaint;
    private int mWidth, mHeght;

    public CanvasView1(Context context) {
        this(context, null);
    }

    public CanvasView1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        /*canvas.drawRect(0, 0, 500, 500, mPaint);
        // 保存Canvas状态
        int saveId = canvas.save();

        // 平移
        canvas.translate(100, 100);
        mPaint.setColor(Color.RED);
        canvas.drawRect(0, 0, 500, 500, mPaint);
        // 再次保存Canvas的状态
        canvas.save();

        // 平移
        canvas.translate(100, 100);
        mPaint.setColor(Color.MAGENTA);
        canvas.drawRect(0, 0, 500, 500, mPaint);

        // 回滚Canvas状态一次
        //canvas.restore();
        // 再次回滚一次到最初的状态
        //canvas.restore();
        // 直接回滚到保存的Id指引的位置，将它栈顶保存的状态全部出栈，将自己放在栈顶
        canvas.restoreToCount(saveId);

        // 绘制直线
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(0, 0, 400, 400, mPaint);*/

        canvas.drawRect(200, 200, 700, 700, mPaint);

        int layerId = canvas.saveLayer(0, 0, 700, 700, mPaint, Canvas.ALL_SAVE_FLAG);
        mPaint.setColor(Color.BLUE);
        Matrix matrix = getMatrix();
        // 平移到(100, 100)的位置
        matrix.setTranslate(100, 100);
        canvas.setMatrix(matrix);
        //由于平移操作，导致绘制的矩形超出了图层的大小，所以绘制不完全
        canvas.drawRect(0, 0, 700, 700, mPaint);
        canvas.restoreToCount(layerId);

        mPaint.setColor(Color.RED);
        canvas.drawRect(0, 0, 100, 100, mPaint);
    }
}
