package com.dh.summarize.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author 86351
 * @date 2019/10/6
 * @description
 */
public class PaintView2 extends View {
    private Context mContext;
    private Paint mPaint;
    private int mWidth;
    private int mHeight;

    public PaintView2(Context context) {
        this(context, null);
    }

    public PaintView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        //mPaint.setXfermode()
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        // 关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);


        int layer = canvas.saveLayer(0, 0, mWidth, mHeight, mPaint, Canvas.ALL_SAVE_FLAG);

        // 目标图dst
        canvas.drawBitmap(createRectBitmap(mWidth, mHeight), 0, 0, mPaint);
        // 设置混合模式
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        // 源图src
        canvas.drawBitmap(createCircleBitmap(mWidth, mHeight), 0, 0, mPaint);

        // 清除混合模式
        mPaint.setXfermode(null);

        canvas.restoreToCount(layer);

        //setLayerType(LAYER_TYPE_HARDWARE, mPaint);
        //setLayerType(LAYER_TYPE_SOFTWARE, mPaint);
    }

    //画矩形Dst
    public Bitmap createRectBitmap(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint dstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dstPaint.setColor(0xFF66AAFF);
        canvas.drawRect(new Rect(width / 20, height / 3, 2 * width / 3, 19 * height / 20), dstPaint);
        return bitmap;
    }

    //画圆src
    public Bitmap createCircleBitmap(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint scrPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        scrPaint.setColor(0xFFFFCC44);
        canvas.drawCircle(width * 2 / 3, height / 3, height / 4, scrPaint);
        return bitmap;
    }
}
