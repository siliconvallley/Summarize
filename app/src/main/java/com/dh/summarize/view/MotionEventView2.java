package com.dh.summarize.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.dh.http.uiils.LogUtils;
import com.dh.summarize.R;

/**
 * @author 86351
 * @date 2019/11/3
 * @description
 * 需要去研究多手势触摸  https://www.gcssloop.com/category/customview
 * https://www.jianshu.com/p/938ca88fb16a
 * https://www.jianshu.com/p/7249c5d2e53f
 */
public class MotionEventView2 extends AppCompatImageView {
    private static final String TAG = "MotionEventView2";

    private Context mContext;
    private float maxScale = 1.5f;
    private float minScale = 0.5f;

    // 标记图片是否平移，缩放，旋转
    private boolean isTranslate;
    private boolean isScale;
    private boolean isRotate;

    // 回弹
    private boolean isTransRebound;
    private boolean isScaleRebound;
    private boolean isRotateRebound;

    // 记录平移的最后一个点
    private PointF mSingleLastPoint;
    // 矩阵，用来对图片进行平移，缩放，旋转
    private Matrix mMatrix;
    // 图片的矩形区域
    private RectF mImgRectF;
    // 当前缩放因子
    private float mScaleFactor = 1.0f;
    // 记录上一次缩放，两点之间的距离
    private float mScaleLastDistance;
    // 保存两个触摸点之间坐标差值，用来计算旋转角度
    private PointF mDiffPoint;


    public MotionEventView2(Context context) {
        this(context, null);
    }

    public MotionEventView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MotionEventView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        // TODO 后续需要继续完善，现在没用到
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.MotionEventView2);
        if (typedArray != null) {
            maxScale = typedArray.getFloat(R.styleable.MotionEventView2_maxScale, maxScale);
            minScale = typedArray.getFloat(R.styleable.MotionEventView2_minScale, minScale);
            typedArray.recycle();
        }

        // 一定要设置图片的缩放类型为矩阵，┭┮﹏┭┮
        setScaleType(ScaleType.MATRIX);

        mSingleLastPoint = new PointF();
        mMatrix = new Matrix();
        mImgRectF = new RectF();

        mDiffPoint = new PointF();
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //LogUtils.INSTANCE.e(TAG,"布局onLayout");
        updateImgPosition();
    }

    private void updateImgPosition() {
        mMatrix.reset();
        // 更新图片所在的矩形区域
        updateImgRect();

        float scaleFactor = Math.min(getWidth() / mImgRectF.width(), getHeight() / mImgRectF.height());
        mScaleFactor = scaleFactor;
        //以图片的中心点进行缩放，缩放图片大小和控件大小适应
        mMatrix.postScale(mScaleFactor, mScaleFactor, mImgRectF.centerX(), mImgRectF.centerY());
        //将图片中心点平移到和控件中心点重合
        mMatrix.postTranslate(getPivotX() - mImgRectF.centerX(), getPivotY() - mImgRectF.centerY());
        //对图片进行变换，并更新图片的边界矩形
        applyMatrix();
    }

    private void updateImgRect() {
        if (getDrawable() != null) {
            mImgRectF.set(getDrawable().getBounds());
            mMatrix.mapRect(mImgRectF, mImgRectF);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                isTranslate = true;
                isScale = false;
                isRotate = false;
                mSingleLastPoint.set(event.getX(), event.getY());
                LogUtils.INSTANCE.e(TAG, "手指个数:" + event.getPointerCount());
                break;
            case MotionEvent.ACTION_POINTER_DOWN:

                LogUtils.INSTANCE.e(TAG, "手指个数:" + event.getPointerCount());
                if (event.getPointerCount() >= 2) {
                    isScale = true;
                    isRotate = true;
                    isTranslate = false;

                    mScaleLastDistance = distance(event);
                    mDiffPoint.set(event.getX(0) - event.getX(1),
                            event.getY(0) - event.getY(1));
                } /*else if (event.getPointerCount() == 1) {
                    isScale = false;
                    isRotate = false;
                    isTranslate = true;

                    mSingleLastPoint.set(event.getX(0), event.getY(0));
                }*/
                break;
            case MotionEvent.ACTION_MOVE:
                if (isTranslate) {
                    translate(event);
                }
                if (isScale) {
                    scale(event);
                }
                if (isRotate) {
                    rotate(event);
                }
                LogUtils.INSTANCE.e(TAG, "图片是否发生改变" + getImageMatrix().equals(mMatrix));
                applyMatrix();
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                isTranslate = false;
                isScale = false;
                isRotate = false;
                break;
        }
        return true;
    }

    private void rotate(MotionEvent event) {
        PointF pointF = new PointF(event.getX(0) - event.getX(1),
                event.getY(0) - event.getY(1));
        // 计算旋转角度
        float rotateAngle = calRotateAngle(mDiffPoint, pointF);
        mMatrix.postRotate(rotateAngle, mImgRectF.centerX(), mImgRectF.centerY());
        mDiffPoint.set(pointF);

    }

    /**
     * 计算两个角度差值，也就是需要旋转的角度
     *
     * @param mDiffPoint
     * @param mCurrentDiffPoint
     */
    private float calRotateAngle(PointF mDiffPoint, PointF mCurrentDiffPoint) {
        double lastAngle = Math.atan2(mDiffPoint.y, mDiffPoint.x);
        double curAngle = Math.atan2(mCurrentDiffPoint.y, mCurrentDiffPoint.x);
        double diffAngle = curAngle - lastAngle;
        return (float) Math.toDegrees(diffAngle);
    }

    /**
     * 缩放，需要知道缩放前后的变换距离
     *
     * @param event
     */
    private void scale(MotionEvent event) {
        float currentDistance = distance(event);
        float scaleFactor = currentDistance / mScaleLastDistance;
        mMatrix.postScale(scaleFactor, scaleFactor, mImgRectF.centerX(), mImgRectF.centerY());
        mScaleLastDistance = currentDistance;
    }

    /**
     * 计算两个点之间的距离
     *
     * @param event
     * @return
     */
    private float distance(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        // 两点间距离公式
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * 将图片变换后的矩阵应用到图片上
     */
    private void applyMatrix() {
        //LogUtils.INSTANCE.e(TAG,"更新矩阵");
        setImageMatrix(mMatrix);
        updateImgRect();
    }

    /**
     * 平移
     *
     * @param event
     */
    private void translate(MotionEvent event) {
        float dx = event.getX() - mSingleLastPoint.x;
        float dy = event.getY() - mSingleLastPoint.y;
        //LogUtils.INSTANCE.e(TAG, "dx:" + dx + "==>dy:" + dy);
        // 平移
        mMatrix.postTranslate(dx, dy);
        // 更新平移之后的点
        mSingleLastPoint.set(event.getX(), event.getY());
    }
}
