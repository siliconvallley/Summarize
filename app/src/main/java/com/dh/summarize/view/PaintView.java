package com.dh.summarize.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.dh.summarize.R;

/**
 * @author 86351
 * @date 2019/10/5
 * @description
 */
public class PaintView extends View {
    private Context context;
    private Paint paint;
    private RectF rectF;
    private int width;
    private int height;
    private Shader shader;
    private Bitmap bitmap;

    public PaintView(Context context) {
        this(context, null);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        init();
    }

    private void init() {
        //rectF = new RectF(-250, -250, 250, 250);
        rectF = new RectF(0, 0, 500, 500);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.beauty);

        // 初始化画笔
        paint = new Paint();
        // 设置颜色
        //paint.setColor(Color.RED);
        paint.setColor(Color.parseColor("#ecab50"));
        //paint.setColor(ContextCompat.getColor(context,R.color.colorAccent));
        // 设置 Paint对象颜色,范围为0~255
        //paint.setARGB(255, 255, 255, 0);
        // 设置alpha不透明度,范围为0~255
        paint.setAlpha(200);
        // 抗锯齿
        paint.setAntiAlias(true);
        //描边效果
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        //描边宽度
        paint.setStrokeWidth(10);
        //圆角效果
        paint.setStrokeCap(Paint.Cap.ROUND);
        //拐角风格
        paint.setStrokeJoin(Paint.Join.MITER);
        //设置扫描渲染器
        //paint.setShader(new SweepGradient(200, 200, Color.BLUE, Color.RED));
        initShader();
        //设置图层混合模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DARKEN));
        //设置颜色过滤器
        /*paint.setColorFilter(new LightingColorFilter(0x00ffff, 0x000000));
        //设置双线性过滤
        paint.setFilterBitmap(true);
        //设置画笔遮罩滤镜 ,传入度数和样式
        paint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));
        // 设置文本缩放倍数
        paint.setTextScaleX(2);
        // 设置字体大小
        paint.setTextSize(38);
        //对其方式
        paint.setTextAlign(Paint.Align.LEFT);
        // 设置下划线
        paint.setUnderlineText(true);

        String str = "Android高级工程师";
        Rect rect = new Rect();
        //测量文本大小，将文本大小信息存放在rect中
        paint.getTextBounds(str, 0, str.length(), rect);
        //获取文本的宽
        paint.measureText(str);
        //获取字体度量对象
        paint.getFontMetrics();*/
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();

    }

    private void initShader() {
        /*
         * 1.线性渲染,LinearGradient(float x0, float y0, float x1, float y1, @NonNull @ColorInt int colors[], @Nullable float positions[], @NonNull TileMode tile)
         * (x0,y0)：渐变起始点坐标
         * (x1,y1):渐变结束点坐标
         * color0:渐变开始点颜色,16进制的颜色表示，必须要带有透明度
         * color1:渐变结束颜色
         * colors:渐变数组
         * positions:位置数组，position的取值范围[0,1],作用是指定某个位置的颜色值，如果传null，渐变就线性变化。
         * tile:用于指定控件区域大于指定的渐变区域时，空白区域的颜色填充方法
         */
        //shader = new LinearGradient(-250, -250, 250, 250, new int[]{Color.RED, Color.GREEN}, null, Shader.TileMode.CLAMP);
        //shader = new LinearGradient(0, 0, 500, 500, new int[]{Color.RED, Color.BLUE, Color.GREEN}, new float[]{0.f,0.7f,1}, Shader.TileMode.REPEAT);


        /*
         * 环形渲染，RadialGradient(float centerX, float centerY, float radius, @ColorInt int colors[], @Nullable float stops[], TileMode tileMode)
         * centerX ,centerY：shader的中心坐标，开始渐变的坐标
         * radius:渐变的半径
         * centerColor,edgeColor:中心点渐变颜色，边界的渐变颜色
         * colors:渐变颜色数组
         * stoops:渐变位置数组，类似扫描渐变的positions数组，取值[0,1],中心点为0，半径到达位置为1.0f
         * tileMode:shader未覆盖以外的填充模式。
         */
        //shader = new RadialGradient(0, 0, 300, new int[]{Color.GREEN, Color.MAGENTA, Color.RED}, null, Shader.TileMode.CLAMP);


        /*
         * 扫描渲染，SweepGradient(float cx, float cy, @ColorInt int color0,int color1)
         * cx,cy 渐变中心坐标
         * color0,color1：渐变开始结束颜色
         * colors，positions：类似LinearGradient,用于多颜色渐变,positions为null时，根据颜色线性渐变
         */
        //shader = new SweepGradient(0, 0, new int[]{Color.MAGENTA, Color.GREEN, Color.YELLOW}, null);


        /*
         * 位图渲染，BitmapShader(@NonNull Bitmap bitmap, @NonNull TileMode tileX, @NonNull TileMode tileY)
         * Bitmap:构造shader使用的bitmap
         * tileX：X轴方向的TileMode
         * tileY:Y轴方向的TileMode
                 REPEAT, 绘制区域超过渲染区域的部分，重复排版
                 CLAMP， 绘制区域超过渲染区域的部分，会以最后一个像素拉伸排版
                 MIRROR, 绘制区域超过渲染区域的部分，镜像翻转排版
         */
        //shader = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);

        /*
         * 组合渲染，
         * ComposeShader(@NonNull Shader shaderA, @NonNull Shader shaderB, Xfermode mode)
         * ComposeShader(@NonNull Shader shaderA, @NonNull Shader shaderB, PorterDuff.Mode mode)
         * shaderA,shaderB:要混合的两种shader
         * Xfermode mode： 组合两种shader颜色的模式
         * PorterDuff.Mode mode: 组合两种shader颜色的模式
         */
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        LinearGradient linearGradient = new LinearGradient(0, 0, 500, 500, new int[]{Color.RED, Color.GREEN, Color.BLUE}, null, Shader.TileMode.CLAMP);
        shader = new ComposeShader(bitmapShader, linearGradient, PorterDuff.Mode.MULTIPLY);

        paint.setShader(shader);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.translate(width / 2, height / 2);


        canvas.drawRect(rectF, paint);
        //canvas.drawCircle(0, 0, 300, paint);


    }
}
