package com.dh.summarize.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.dh.summarize.R;
import com.dh.utils_library.utils.LogUtils;

/**
 * @author 86351
 * @date 2019/10/20
 * @description PathMeasure：path的测量类
 */
public class PathView3 extends View {
    private static final String TAG = "PathView3";
    private Paint mCoorPaint;
    private Paint mPaint;
    private int mWidth, mHeight;

    private Path mPath;
    private float chgValue;
    private RectF mRectF;
    private Bitmap mBitmap;
    private Matrix mMatrix;

    private float[] pos = new float[2];
    private float[] tan = new float[2];

    public PathView3(Context context) {
        this(context, null);
    }

    public PathView3(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mCoorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCoorPaint.setColor(Color.GREEN);
        mCoorPaint.setStyle(Paint.Style.STROKE);
        mCoorPaint.setStrokeWidth(4);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);

        mPath = new Path();
        mRectF = new RectF(-300, -300, 300, 300);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.arrow, options);

        mMatrix = new Matrix();
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

        canvas.drawLine(0, mHeight / 2, mWidth, mHeight / 2, mCoorPaint);
        canvas.drawLine(mWidth / 2, 0, mWidth / 2, mHeight, mCoorPaint);
        canvas.translate(mWidth / 2, mHeight / 2);

        /*Path path = new Path();
        path.moveTo(-100, -100);
        path.lineTo(100, -100);
        path.lineTo(100, 100);
        path.lineTo(-100, 100);


        // pathMeasure需要关联一个创建好的path, forceClosed会影响Path的测量结果
        // 两种方式，一种是在构造方法中直接关联，另一种是调用pathMeasure.setPath
        // PathMeasure pathMeasure = new PathMeasure(path, false);
        PathMeasure pathMeasure = new PathMeasure();
        //pathMeasure.setPath(path, false);
        // 测量出来path的结果是600
        //LogUtils.d(TAG, "forceClosed:false" + "====Path的长度:" + pathMeasure.getLength());
        // 测量出来结果是800
        pathMeasure.setPath(path, true);
        LogUtils.d(TAG, "forceClosed:true" + "====Path的长度:" + pathMeasure.getLength());

        path.lineTo(-200, 100);
        // 如果Path进行了调整，需要重新调用setPath方法进行关联
        pathMeasure.setPath(path, true);
        LogUtils.d(TAG, "forceClosed:true" + "====Path的长度:" + pathMeasure.getLength());*/

        /*Path path = new Path();
        path.addRect(new RectF(-200, -200, 200, 200), Path.Direction.CW);
        Path dstPath = new Path();
        dstPath.lineTo(-200,-200);

        PathMeasure pathMeasure = new PathMeasure(path, false);
        // 截取一部分存入dst中，并且使用moveTo保持截取得到的Path第一个点位置不变。
        //pathMeasure.getSegment(200, 1000, dstPath, false);
        pathMeasure.getSegment(200, 1000, dstPath, true);

        canvas.drawPath(path, mPaint);
        canvas.drawPath(dstPath, mCoorPaint);*/

        /*Path path = new Path();
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
        path.addOval(new RectF(-300, -300, 300, 300), Path.Direction.CW);

        PathMeasure pathMeasure = new PathMeasure(path, true);

        // 长度是1600，我们发现path的长度只包含了矩形的周长
        // 因为pathMeasure.getLength()只会获取当前Path的长度
        LogUtils.d(TAG, "Path的长度:" + pathMeasure.getLength());

        // 跳转到下一个Path，也就是椭圆
        pathMeasure.nextContour();
        // 长度1884.1941，就是椭圆的周长2*R*PI
        LogUtils.d(TAG, "Path的长度:" + pathMeasure.getLength());*/

        /*Path path = new Path();
        path.addOval(new RectF(-300, -300, 300, 300), Path.Direction.CW);

        PathMeasure pathMeasure = new PathMeasure(path, true);
        pathMeasure.getPosTan(200, pos, tan);
        // pos[0]:235.72583==>pos[1]:185.56221
        LogUtils.d(TAG, "pos[0]:" + pos[0] + "==>pos[1]:" + pos[1]);
        // tan[0]:-0.6185407==>tan[1]:0.7857528
        LogUtils.d(TAG, "tan[0]:" + tan[0] + "==>tan[1]:" + tan[1]);

        // 计算出当前切线去X轴的夹角度数
        double degreess = Math.atan2(tan[1], tan[0]) * 180;

        canvas.drawPath(path, mPaint);*/

        // 重置path
        mPath.reset();
        // mPath.addOval(mRectF, Path.Direction.CW);
        mPath.addCircle(0, 0, 300, Path.Direction.CW);
        canvas.drawPath(mPath, mPaint);

        // 修改chgValue的值，每次切点才会移动
        chgValue += 0.01;
        if (chgValue >= 1) {
            chgValue = 0;
        }


        /*PathMeasure pathMeasure = new PathMeasure(mPath, true);
        pathMeasure.getPosTan(pathMeasure.getLength() * chgValue, pos, tan);
        // pos[0]:235.72583==>pos[1]:185.56221
        LogUtils.d(TAG, "pos[0]:" + pos[0] + "==>pos[1]:" + pos[1]);
        // tan[0]:-0.6185407==>tan[1]:0.7857528
        LogUtils.d(TAG, "tan[0]:" + tan[0] + "==>tan[1]:" + tan[1]);

        // 计算出当前切线去X轴的夹角度数
        double degree = Math.atan2(tan[1], tan[0]) * 180 / Math.PI;
        LogUtils.d(TAG, "旋转角度:" + degree);

        // 一定需要将mMatrix重置
        mMatrix.reset();
        // 修改旋转角度
        mMatrix.postRotate((float) degree, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
        // 将当前图片的绘制中心移动到跟切点重合
        mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);*/


        PathMeasure pathMeasure = new PathMeasure(mPath, false);
        // 根据两个标志，直接将旋转角度和旋转位置保存在Matrix中
        // 就是将pos信息和tan信息保存在mMatrix中
        pathMeasure.getMatrix(
                pathMeasure.getLength() * chgValue,
                mMatrix,
                PathMeasure.POSITION_MATRIX_FLAG | PathMeasure.TANGENT_MATRIX_FLAG
        );
        // 修正图片旋转中心坐标为图片的中心
        mMatrix.preTranslate(-mBitmap.getWidth()/2,-mBitmap.getHeight()/2);
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);

        invalidate();
    }

}
