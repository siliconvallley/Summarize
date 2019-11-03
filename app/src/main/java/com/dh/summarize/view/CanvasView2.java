package com.dh.summarize.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.dh.summarize.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 86351
 * @date 2019/10/8
 * @description 粒子爆炸
 */
public class CanvasView2 extends View {
    private Paint mPaint;
    private Bitmap mBitmap;
    private List<Cell> cells;
    private float defaultRadius = 1.5f;
    private ValueAnimator mAnimator;

    private int mWidth, mHeight;

    public CanvasView2(Context context) {
        this(context, null);
    }

    public CanvasView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        cells = new ArrayList<>();

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.beauty);
        int bmWidth = mBitmap.getWidth();
        int bmHeight = mBitmap.getHeight();
        for (int i = 0; i < bmWidth; i++) {
            for (int j = 0; j < bmHeight; j++) {
                Cell cell = new Cell();
                // 获取像素点颜色
                cell.color = mBitmap.getPixel(i, j);
                cell.radius = defaultRadius;
                cell.x = i * 2 * defaultRadius + defaultRadius;
                cell.y = j * 2 * defaultRadius + defaultRadius;
                cells.add(cell);

                // 速度(-20,20)
                cell.vx = (float) (Math.pow(-1, Math.ceil(Math.random() * 1000)) * 20 * Math.random());
                cell.vy = rangInt(-15, 25);
            }
        }

        // 初始化动画
        mAnimator = ValueAnimator.ofFloat(0, 1);
        mAnimator.setRepeatCount(-1);
        mAnimator.setDuration(2000);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(animation -> {
            updateCell();
            postInvalidate();
        });
    }

    private int rangInt(int i, int j) {
        int max = Math.max(i, j);
        int min = Math.min(i, j) - 1;
        //在0到(max - min)范围内变化，取大于x的最小整数 再随机
        return (int) (min + Math.ceil(Math.random() * (max - min)));
    }

    private void updateCell() {
        //更新粒子的位置
        for (Cell cell : cells) {
            cell.x += cell.vx;
            cell.y += cell.vy;

            cell.vx += cell.ax;
            cell.vy += cell.ay;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mWidth / 4, mHeight / 6);

        for (Cell cell : cells) {
            mPaint.setColor(cell.color);
            canvas.drawCircle(cell.x, cell.y, defaultRadius, mPaint);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mAnimator.start();
        }
        return super.onTouchEvent(event);
    }
}
