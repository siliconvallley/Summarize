package com.dh.summarize.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.dh.summarize.R;

/**
 * @author 86351
 * @date 2019/10/27
 * @description
 */
public class PaintView7 extends View {
    private Bitmap mBitmap;
    private Paint mPaint;

    public PaintView7(Context context) {
        this(context,null);
    }

    public PaintView7(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PaintView7(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.girl);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        // R = 0.213f
        // G = 0.715f
        // B = 0.072f

        float[] colorMatrix = {
                // 最后一列是偏移量
                // R,G,B,A
                0.213f, 0.715f, 0.072f, 0, 0, // red
                0.213f, 0.715f, 0.072f, 0, 0, // green
                0.213f, 0.715f, 0.072f, 0, 0, // blue
                0, 0, 0, 1, 0  // alpha
        };

        /*
        m[0] = R + sat; m[1] = G;       m[2] = B;
        m[5] = R;       m[6] = G + sat; m[7] = B;
        m[10] = R;      m[11] = G;      m[12] = B + sat;
        */

        // 我们可以调节饱和度来改变颜色色彩，因为0是无色彩也就是灰色
        /*ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);*/

        ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
        mPaint.setColorFilter(colorMatrixColorFilter);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
    }
}
