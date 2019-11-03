package com.dh.summarize.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author 86351
 * @date 2019/10/10
 * @description
 */
public class PathView extends View {
    private Paint mPaint;
    private Path mPath;

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.MAGENTA);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*// 一阶贝塞尔曲线，就是一条直线
        // 移动起点到(100, 100)
        mPath.moveTo(100, 100);
        // 从坐标原点连线到(300, 400)
        mPath.lineTo(300, 400);
        mPath.lineTo(300, 100);
        // 闭合Path
        mPath.close();

        mPath.moveTo(400,100);
        //mPath.lineTo(700,500);
        // 等同于lineTo(700,500)，相对于前一个点的位置
        mPath.rLineTo(300,400);

        // 将当点移动到(400,500)
        // 相当于mPath.moveTo(400,500);
        mPath.rMoveTo(-300,0);
        mPath.rLineTo(100,300);
        // 改变(500, 800)这个点到(500, 700)
        mPath.setLastPoint(500,700);
        mPath.lineTo(500,500);
        mPath.close();*/

        /*// 添加图形
        // >=21可以使用下面这个方法
        //mPath.addArc(100, 100, 500, 500, 30, 140);
        // 绘制一段圆弧，开始角度30，扫过140度
        mPath.addArc(new RectF(100, 100, 500, 500), 30, 140);
        //mPath.close();

        // >=21
        //mPath.addOval(500, 100, 700, 600, Path.Direction.CW);
        // 绘制椭圆Path.Direction.CW顺时针，Path.Direction.CCW逆时针
        mPath.addOval(new RectF(500, 100, 700, 600), Path.Direction.CW);

        // 绘制圆形
        mPath.addCircle(400, 400, 200, Path.Direction.CCW);

        // 通过下面的绘制我们会发现，顺时针和逆时针还是会造成很大区别的
        // 绘制矩形
        //mPath.addRect(400, 600, 800, 900, Path.Direction.CW);
        mPath.addRect(400, 600, 800, 900, Path.Direction.CCW);
        // 修改最后一个点的位置
        mPath.setLastPoint(300, 1000);
        //mPath.addRect(new RectF(100, 600, 600, 900), Path.Direction.CW);

        // 追加图形
        // >=21
        //mPath.arcTo(200, 200, 500, 600, 50, 200, false);
        // forceMoveTo 为true，绘制时移动起点，为false，绘制时，将绘制圆弧之前的最后一个点与圆弧的起点相连
        mPath.arcTo(new RectF(200, 600, 600, 900), 50, 150, false);

        // 添加一个Path
        Path newPath = new Path();
        newPath.moveTo(100,500);
        newPath.lineTo(700,900);
        // 添加Path
        mPath.addPath(newPath);*/

        mPath.moveTo(200,200);
        // 二阶贝塞尔曲线
        //mPath.quadTo(200,400,500,500);
        // 跟上面这句代码一致，相对位置
        mPath.rQuadTo(0,200,300,300);

        mPath.moveTo(200,550);
        // 三阶贝塞尔曲线
        //mPath.cubicTo(200,200,400,800,800,400);
        mPath.rCubicTo(0,-350,200,250,600,-150);


        canvas.drawPath(mPath, mPaint);
    }
}
