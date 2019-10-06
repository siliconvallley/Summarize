package com.dh.summarize.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.dh.summarize.R;

/**
 * @author 86351
 * @date 2019/10/6
 * @description
 */
public class PaintView5 extends View {
    private Context mContext;
    private Paint mPaint;
    private Bitmap mDstBmp, mSrcBmp, mTxtBmp;
    private Path mPath;

    private float mEventX, mEventY;


    public PaintView5(Context context) {
        this(context, null);
    }

    public PaintView5(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintView5(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.mContext = context;
        init();
    }

    private void init() {
        //初始化画笔
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(80);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        //禁用硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        // 结果Bitmap，就是刮开之后显示的文字
        mTxtBmp = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.result);
        // 源位图（蒙层）
        mSrcBmp = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.eraser);
        // 目标位图（跟蒙图进行混合的位图）
        mDstBmp = Bitmap.createBitmap(mSrcBmp.getWidth(), mSrcBmp.getHeight(), Bitmap.Config.ARGB_8888);

        //路径（贝塞尔曲线）
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制刮奖结果
        canvas.drawBitmap(mTxtBmp, 0, 0, mPaint);

        // 使用离屏缓冲
        int layerId = canvas.saveLayer(new RectF(0, 0, getWidth(), getHeight()), mPaint, Canvas.ALL_SAVE_FLAG);

        //先将路径绘制到bitmap上（在目标位图绘制曲线）
        Canvas dstCanvas = new Canvas(mDstBmp);
        dstCanvas.drawPath(mPath, mPaint);
        // 不要直接在View的canvas上绘制，因为会出现绘制到刮奖区域之外的地方
        // canvas.drawPath(mPath, mPaint);

        // 绘制目标图像
        canvas.drawBitmap(mDstBmp, 0, 0, mPaint);
        //设置 模式 为 SRC_OUT, 擦橡皮区域为交集区域需要清掉像素
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        //绘制源图像
        canvas.drawBitmap(mSrcBmp, 0, 0, mPaint);

        mPaint.setXfermode(null);


        canvas.restoreToCount(layerId);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mEventX = event.getX();
                mEventY = event.getY();
                // 移动path的绘制起始点到手指按下的地方
                mPath.moveTo(mEventX, mEventY);
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = (event.getX() - mEventX) / 2 + mEventX;
                float endY = (event.getY() - mEventY) / 2 + mEventY;
                //float endX = event.getX();
                //float endY = event.getY();
                // 绘制二阶贝塞尔曲线
                mPath.quadTo(mEventX, mEventY, endX, endY);
                mEventX = event.getX();
                mEventY = event.getY();

                //mEventX = endX;
                //mEventY = endY;
                break;
        }
        invalidate();
        // 消费触摸事件
        return true;
    }
}
