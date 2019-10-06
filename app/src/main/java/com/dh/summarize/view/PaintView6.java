package com.dh.summarize.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.dh.summarize.R;

/**
 * @author 86351
 * @date 2019/10/6
 * @description
 */
public class PaintView6 extends View {
    private Paint mPaint;
    private Bitmap mBitmap;

    public PaintView6(Context context) {
        this(context, null);
    }

    public PaintView6(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintView6(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.girl);

        /*
         * LightingColorFilter(int mul, int add)
         * R' = R * mul.R / 0xff + add.R
         * G' = G * mul.G / 0xff + add.G
         * B' = B * mul.B / 0xff + add.B
         */
        // 原图
        // LightingColorFilter lightingColorFilter = new LightingColorFilter(0xffffff,0x000000);
        // 绿色加深
        //LightingColorFilter lightingColorFilter = new LightingColorFilter(0xffffff,0x003000);
        // 改变红色
        // LightingColorFilter lightingColorFilter = new LightingColorFilter(0x11ffff,0x000000);
        // mPaint.setColorFilter(lightingColorFilter);


        // PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(Color.parseColor("#ff0000"), PorterDuff.Mode.DARKEN);
        // 设置颜色的时候，需要设置Alpha通道，混合模式就是去修改Alpha通道的值，否则有的模式不会有效果的改变
        //PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(0xaaffff00, PorterDuff.Mode.SCREEN);
        //mPaint.setColorFilter(porterDuffColorFilter);

        // 原图
        /*float[] colorMatrix = {
                // 最后一列是偏移量
                1, 0, 0, 0, 0, // red
                0, 1, 0, 0, 0, // green
                0, 0, 1, 0, 0, // blue
                0, 0, 0, 1, 0  // alpha
        };*/

        // 如果我们需要改变各种效果，直接修改矩阵中的值
        /*float[] colorMatrix = {
                // 最后一列是偏移量
                1, 1, 0, 0, 0, // red
                0, 2, 0, 0, 0, // green
                0, 1, 1, 0, 0, // blue
                0, 0, 0, 1, 0  // alpha
        };*/

        ColorMatrix colorMatrix = new ColorMatrix();
        // 调节亮度，刚好改变的原图中矩阵中对角线的值
        //colorMatrix.setScale(1.2f,1f,1.5f,2);

        // 调节饱和度 0-无色彩 1-默认效果 >1-饱和度增强
        //colorMatrix.setSaturation(2);

        //色度调节
        colorMatrix.setRotate(1,50);

        ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
        mPaint.setColorFilter(colorMatrixColorFilter);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
    }
}
