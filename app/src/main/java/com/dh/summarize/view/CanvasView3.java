package com.dh.summarize.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import androidx.annotation.Nullable;

import com.dh.summarize.R;

/**
 * @author 86351
 * @date 2019/10/9
 * @description
 * 加载动画：
 * Progress（6个小圆组成），Progress离散聚合动画，最后绘制一个水波纹显示正文
 *
 */
public class CanvasView3 extends View {
    // 旋转圆的画笔（小圆球）
    private Paint mPaint;
    // 水波纹圆的画笔
    private Paint mRipplePaint;
    // 属性动画
    private ValueAnimator mValueAnimator;

    // 背景色
    private int mBgColor = Color.WHITE;
    // 6个小圆的颜色
    private int[] mCircleColors;

    //表示旋转圆的中心坐标（6个小球围成的圆）
    private float mCenterX;
    private float mCenterY;
    //表示斜对角线长度的一半,扩散圆最大半径
    private float mDistance;

    //6个小球的半径
    private float mCircleRadius = 18;
    //旋转大圆的半径
    private float mRotateRadius = 90;

    //当前大圆的旋转角度（默认是0）
    private float mCurrentRotateAngle = 0F;
    //当前大圆的半径（半径是会变化的）
    private float mCurrentRotateRadius = mRotateRadius;
    //扩散圆的半径
    private float mCurrentRippleRadius = 0F;
    //表示旋转动画的时长
    private int mRotateDuration = 1200;

    private SplashState mState;

    public CanvasView3(Context context) {
        this(context, null);
    }

    public CanvasView3(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mRipplePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRipplePaint.setStyle(Paint.Style.STROKE);
        mRipplePaint.setColor(mBgColor);

        mCircleColors = getResources().getIntArray(R.array.splash_circle_colors);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w * 1f / 2;
        mCenterY = h * 1f / 2;
        // sqrt(x^2 + y^2)
        mDistance = (float) (Math.hypot(w, h) / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mState == null) {
            mState = new RotateState();
        }
        mState.drawState(canvas);
    }

    /**
     * 绘制状态的类
     */
    private abstract class SplashState {
        abstract void drawState(Canvas canvas);
    }


    /**
     * 1、旋转状态，就是6个旋转小圆
     */
    private class RotateState extends SplashState {

        private RotateState() {
            // 属性动画，并且设置动画的取值范围
            mValueAnimator = ValueAnimator.ofFloat(0, (float) (2 * Math.PI));
            // 执行次数
            mValueAnimator.setRepeatCount(2);
            mValueAnimator.setDuration(mRotateDuration);
            // 插值器
            mValueAnimator.setInterpolator(new LinearInterpolator());
            // 监听动画执行
            mValueAnimator.addUpdateListener(animation -> {
                // 更新旋转角度(因为我们的动画范围刚好就是0..2PI)
                mCurrentRotateAngle = (float) animation.getAnimatedValue();
                invalidate();
            });
            mValueAnimator.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mState = new DiffState();
                }

                // 动画结束的时候，并没有走这个结束监听
                /*@Override
                public void onAnimationEnd(Animator animation, boolean isReverse) {
                    mState = new DiffState();
                }*/
            });
            mValueAnimator.start();
        }

        @Override
        void drawState(Canvas canvas) {
            //绘制背景
            drawBackground(canvas);
            //绘制6个小球
            drawCircles(canvas);
        }
    }

    /**
     * 绘制6个小圆
     *
     * @param canvas
     */
    private void drawCircles(Canvas canvas) {
        // 每个小球之间的角度（这里求的是弧度）
        float rotateAngle = (float) (Math.PI * 2 / mCircleColors.length);
        for (int i = 0; i < mCircleColors.length; i++) {
            //float angle = i * rotateAngle;
            // 小球的角度(因为旋转动画，所以我们需要加上旋转过的角度)
            float angle = i * rotateAngle + mCurrentRotateAngle;
            // 小球的圆心坐标
            // x = r * cos(a) + centX;
            // y = r * sin(a) + centY;
            //float cx = (float) (Math.cos(angle) * mRotateRadius + mCenterX);
            //float cy = (float) (Math.sin(angle) * mRotateRadius + mCenterY);
            // 更改旋转圆的半径，因为扩散的时候，半径一直在变化，所以相应的小圆的圆心坐标也需要改变
            float cx = (float) (Math.cos(angle) * mCurrentRotateRadius + mCenterX);
            float cy = (float) (Math.sin(angle) * mCurrentRotateRadius + mCenterY);
            mPaint.setColor(mCircleColors[i]);
            canvas.drawCircle(cx, cy, mCircleRadius, mPaint);
        }
    }

    /**
     * 绘制背景
     *
     * @param canvas
     */
    private void drawBackground(Canvas canvas) {
        // 判断当水波纹圆半径大于0，说明走到第三步绘制水波纹圆
        if (mCurrentRippleRadius > 0) {
            // 空心圆边框宽度
            float strokeWidth = mDistance - mCurrentRippleRadius;
            // 空心圆半径
            float radius = strokeWidth / 2 + mCurrentRippleRadius;
            mRipplePaint.setStrokeWidth(strokeWidth);
            canvas.drawCircle(mCenterX, mCenterY, radius, mRipplePaint);
        } else {
            canvas.drawColor(mBgColor);
        }
    }


    /**
     * 2、扩散状态
     */
    private class DiffState extends SplashState {

        private DiffState() {
            // 动画取值范围，小圆半径到大圆半径
            mValueAnimator = ValueAnimator.ofFloat(mCircleRadius, mRotateRadius);
            // 执行次数
            //mValueAnimator.setRepeatCount(2);
            mValueAnimator.setDuration(mRotateDuration);
            // 插值器
            mValueAnimator.setInterpolator(new OvershootInterpolator(10f));
            // 监听动画执行
            mValueAnimator.addUpdateListener(animation -> {
                // 更新当前旋转圆的半径
                mCurrentRotateRadius = (float) animation.getAnimatedValue();
                invalidate();
            });
            mValueAnimator.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animation) {
                    mState = new RippleState();
                }
            });
            mValueAnimator.reverse();
        }

        @Override
        void drawState(Canvas canvas) {
            drawBackground(canvas);
            drawCircles(canvas);
        }
    }

    private class RippleState extends SplashState {

        private RippleState() {
            // 动画取值范围，小圆半径到水波纹圆的半径
            mValueAnimator = ValueAnimator.ofFloat(mCircleRadius, mDistance);
            // 执行次数
            //mValueAnimator.setRepeatCount(2);
            mValueAnimator.setDuration(mRotateDuration);
            // 插值器
            mValueAnimator.setInterpolator(new LinearInterpolator());
            // 监听动画执行
            mValueAnimator.addUpdateListener(animation -> {
                // 更新当前旋转圆的半径
                mCurrentRippleRadius = (float) animation.getAnimatedValue();
                invalidate();
            });
            mValueAnimator.start();
        }

        @Override
        void drawState(Canvas canvas) {
            drawBackground(canvas);
        }
    }
}
