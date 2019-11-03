package com.dh.summarize.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.PointFEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.dh.summarize.R;

/**
 * @author 86351
 * @date 2019/10/16
 * @description  贝塞尔曲线例子，仿QQ消息爆炸效果
 */
public class PathView2 extends View {
    private Context mContext;

    /**
     * 汽包的四个状态
     * 默认，连接，断开，消失
     */
    private enum State {
        BUBBLE_STATE_DEFAULT,
        BUBBLE_STATE_CONNECT,
        BUBBLE_STATE_APART,
        BUBBLE_STATE_DISMISS
    }

    /**
     * 气泡半径
     */
    private float mBubbleRadius;
    /**
     * 气泡颜色
     */
    private int mBubbleColor;
    /**
     * 气泡消息文字
     */
    private String mBubbleText;
    /**
     * 气泡消息文字颜色
     */
    private int mTextColor;
    /**
     * 气泡消息文字大小
     */
    private float mTextSize;


    /**
     * 不动气泡的半径，半径是可变的
     */
    private float mBubFixedRadius;
    /**
     * 不动气泡的圆心
     */
    private PointF mBubFixedCenter;

    /**
     * 可动气泡的半径
     */
    private float mBubMovableRadius;
    /**
     * 可动气泡的圆心
     */
    private PointF mBubMovableCenter;

    /**
     * 气泡的画笔
     */
    private Paint mBubblePaint;
    /**
     * 贝塞尔曲线path
     */
    private Path mBezierPath;
    /**
     * 文字画笔
     */
    private Paint mTextPaint;
    /**
     * 文本绘制区域
     */
    private Rect mTextRect;
    /**
     * 爆炸效果画笔
     */
    private Paint mBurstPaint;
    /**
     * 爆炸效果绘制区域
     */
    private Rect mBurstRect;
    /**
     * 气泡状态标志
     */
    private State mBubbleState = State.BUBBLE_STATE_DEFAULT;
    /**
     * 两气泡圆心距离，可变的
     */
    private float mCenterDist;
    /**
     * 气泡相连状态最大圆心距离
     */
    private float mMaxDist;
    /**
     * 手指触摸偏移量
     */
    private float MOVE_OFFSET;
    /**
     * 气泡爆炸的bitmap数组
     */
    private Bitmap[] mBurstBitmapsArray;
    /**
     * 当前气泡爆炸图片index
     */
    private int mBurstImgIndex;
    /**
     * 气泡爆炸的图片id数组
     */
    private int[] mBurstImgArray;


    public PathView2(Context context) {
        this(context, null);
    }

    public PathView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.PathView2, defStyleAttr, 0);
        mBubbleRadius = typedArray.getDimension(R.styleable.PathView2_bubble_radius, mBubbleRadius);
        mBubbleColor = typedArray.getColor(R.styleable.PathView2_bubble_color, Color.RED);
        mBubbleText = typedArray.getString(R.styleable.PathView2_bubble_text);
        mTextSize = typedArray.getDimension(R.styleable.PathView2_bubble_textSize, mTextSize);
        mTextColor = typedArray.getColor(R.styleable.PathView2_bubble_textColor, Color.WHITE);
        typedArray.recycle();


        // 初始的时候两个圆的半径一致
        mBubFixedRadius = mBubbleRadius;
        mBubMovableRadius = mBubbleRadius;
        // 设置两个圆心最大距离
        mMaxDist = mBubbleRadius * 8;
        // 手指触摸的偏移量
        MOVE_OFFSET = mMaxDist / 4;

        mBurstImgArray = new int[]{
                R.mipmap.burst_1,
                R.mipmap.burst_2,
                R.mipmap.burst_3,
                R.mipmap.burst_4,
                R.mipmap.burst_5
        };

        // 气泡画笔
        mBubblePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBubblePaint.setColor(mBubbleColor);
        mBubblePaint.setStyle(Paint.Style.FILL);
        mBezierPath = new Path();

        //文本画笔
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
        // 文本绘制区域
        mTextRect = new Rect();

        //爆炸画笔
        mBurstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBurstPaint.setFilterBitmap(true);
        // 爆炸效果绘制区域
        mBurstRect = new Rect();
        mBurstBitmapsArray = new Bitmap[mBurstImgArray.length];
        for (int i = 0; i < mBurstImgArray.length; i++) {
            // 将气泡爆炸的drawable转为bitmap
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), mBurstImgArray[i]);
            mBurstBitmapsArray[i] = bitmap;
        }
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);

        // 不动圆气泡圆心，在View的中心
        if (mBubFixedCenter == null) {
            mBubFixedCenter = new PointF(w / 2, h / 2);
        } else {
            mBubFixedCenter.set(new PointF(w / 2, h / 2));
        }

        // 初始的时候可动圆气泡圆心，在View的中心
        if (mBubMovableCenter == null) {
            mBubMovableCenter = new PointF(w / 2, h / 2);
        } else {
            mBubMovableCenter.set(new PointF(w / 2, h / 2));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //1，静止状态，一个气泡加消息数据
        //2, 连接状态，一个气泡加消息数据，贝塞尔曲线，本身位置上气泡，大小可变化
        //3，分离状态，一个气泡加消息数据
        //4，消失状态，爆炸效果

        if (mBubbleState == State.BUBBLE_STATE_CONNECT) {
            // 绘制不动圆
            canvas.drawCircle(mBubFixedCenter.x, mBubFixedCenter.y, mBubbleRadius, mBubblePaint);

            // 计算控制点
            int controlX = (int) ((mBubFixedCenter.x + mBubMovableCenter.x) / 2);
            int controlY = (int) ((mBubFixedCenter.y + mBubMovableCenter.y) / 2);

            // 计算两圆连线与X轴的夹角a，获取他们的正余弦函数值，用来求解A，B，C，D的坐标
            // 从B点画垂直线到X轴与X轴相交于B'，B点连接圆心O'，因此根据数学知识我们可以推导出，BB'与BO'的夹角就是夹角a的大小
            float sinA = (mBubMovableCenter.y - mBubFixedCenter.y) / mCenterDist;
            float cosA = (mBubMovableCenter.x - mBubFixedCenter.x) / mCenterDist;

            // A 固定圆上的点
            float aX = mBubFixedCenter.x + mBubFixedRadius * sinA;
            float aY = mBubFixedCenter.y - mBubFixedRadius * cosA;
            // B
            float bX = mBubMovableCenter.x + mBubMovableRadius * sinA;
            float bY = mBubMovableCenter.y - mBubMovableRadius * cosA;
            // C
            float cX = mBubMovableCenter.x - mBubMovableRadius * sinA;
            float cY = mBubMovableCenter.y + mBubMovableRadius * cosA;
            // D 固定圆上的点
            float dX = mBubFixedCenter.x - mBubFixedRadius * sinA;
            float dY = mBubFixedCenter.y + mBubFixedRadius * cosA;

            mBezierPath.reset();
            mBezierPath.moveTo(aX, aY);
            mBezierPath.quadTo(controlX, controlY, bX, bY);

            mBezierPath.lineTo(cX, cY);
            mBezierPath.quadTo(controlX, controlY, dX, dY);
            mBezierPath.close();

            canvas.drawPath(mBezierPath, mBubblePaint);
        }

        // 只要不是消失状态都需要绘制文本和移动圆
        if (mBubbleState != State.BUBBLE_STATE_DISMISS) {
            // 绘制移动圆
            canvas.drawCircle(mBubMovableCenter.x, mBubMovableCenter.y, mBubMovableRadius, mBubblePaint);
            // 获取文本占用区域
            mTextPaint.getTextBounds(mBubbleText, 0, mBubbleText.length(), mTextRect);
            // 绘制文本
            canvas.drawText(
                    mBubbleText,
                    mBubMovableCenter.x - (float) (mTextRect.width() / 2),
                    mBubMovableCenter.y + (float) (mTextRect.height() / 2),
                    mTextPaint
            );
        }

        if (mBubbleState == State.BUBBLE_STATE_DISMISS && mBurstImgIndex < mBurstBitmapsArray.length) {
            // 设置爆炸效果绘制区域
            mBurstRect.set(
                    (int) (mBubMovableCenter.x - mBubMovableRadius),
                    (int) (mBubMovableCenter.y - mBubMovableRadius),
                    (int) (mBubMovableCenter.x + mBubMovableRadius),
                    (int) (mBubMovableCenter.y + mBubMovableRadius)
            );
            canvas.drawBitmap(mBurstBitmapsArray[mBurstImgIndex], null, mBurstRect, mBurstPaint);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mBubbleState != State.BUBBLE_STATE_DISMISS) {
                    // 求圆心距离
                    mCenterDist = (float) Math.hypot(event.getX() - mBubFixedCenter.x, event.getY() - mBubFixedCenter.y);

                    // 当小于mBubbleRadius + MOVE_OFFSET，我们认为点到了，否则就没点击到
                    // 加上MOVE_OFFSET增加触摸点面积
                    if (mCenterDist < mBubbleRadius + MOVE_OFFSET) {
                        mBubbleState = State.BUBBLE_STATE_CONNECT;
                    } else {
                        // 因为没有被点击到，所以是默认状态
                        mBubbleState = State.BUBBLE_STATE_DEFAULT;
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mBubbleState != State.BUBBLE_STATE_DEFAULT) {
                    // 求圆心距离
                    mCenterDist = (float) Math.hypot(event.getX() - mBubFixedCenter.x, event.getY() - mBubFixedCenter.y);
                    // 给移动圆圆心重新赋值
                    mBubMovableCenter.x = event.getX();
                    mBubMovableCenter.y = event.getY();
                    // 判断是否是连接状态
                    if (mBubbleState == State.BUBBLE_STATE_CONNECT) {
                        if (mCenterDist < mMaxDist - MOVE_OFFSET) {
                            // 当拖拽的距离在指定范围内，那么调整不动气泡的半径
                            // 修改固定圆半径，随着圆心距离增加，固定圆半径越来越小
                            mBubFixedRadius = mBubbleRadius - mCenterDist / 8;
                        } else {
                            //当拖拽的距离超过指定范围，那么改成分离状态
                            mBubbleState = State.BUBBLE_STATE_APART;
                        }
                    }
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                // 如果连接状态，需要回弹
                if (mBubbleState == State.BUBBLE_STATE_CONNECT) {
                    //橡皮筋动画效果
                    startBubbleRestAnim();
                } else if (mBubbleState == State.BUBBLE_STATE_APART) {
                    // 判断松手的位置
                    if (mCenterDist < mMaxDist) {
                        startBubbleRestAnim();
                    } else {
                        //爆炸效果
                        startBubbleBurstAnim();
                    }
                }
                break;
        }

        return true;
    }

    /**
     * 爆炸动画
     */
    private void startBubbleBurstAnim() {
        mBubbleState = State.BUBBLE_STATE_DISMISS;
        // 设置爆炸动画从0到图片的长度
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, mBurstBitmapsArray.length);
        valueAnimator.setDuration(500);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(animation -> {
            // 获取图片的小标
            mBurstImgIndex = (int) animation.getAnimatedValue();
            invalidate();
        });
        valueAnimator.start();
    }

    /**
     * 回弹动画
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void startBubbleRestAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofObject(
                new PointFEvaluator(),
                new PointF(mBubMovableCenter.x, mBubMovableCenter.y),
                new PointF(mBubFixedCenter.x, mBubFixedCenter.y)
        );
        valueAnimator.setDuration(300);
        valueAnimator.setInterpolator(new OvershootInterpolator(5f));
        valueAnimator.addUpdateListener(animation -> {
            mBubMovableCenter = (PointF) animation.getAnimatedValue();
            invalidate();
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // 回弹动画执行完，修改当前气泡的状态为默认状态
                mBubbleState = State.BUBBLE_STATE_DEFAULT;
            }
        });
        valueAnimator.start();
    }
}
