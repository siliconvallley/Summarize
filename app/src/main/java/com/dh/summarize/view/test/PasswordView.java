package com.dh.summarize.view.test;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.Editable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import androidx.appcompat.widget.AppCompatEditText;
import com.dh.summarize.R;

/**
 * @author 86351
 * @date 2019/11/3
 * @description  密码控件
 */
public class PasswordView extends AppCompatEditText {
    private static final int DEFAULT_PASS_NUM = 6;
    private static final int DEFAULT_COLOR = Color.BLACK;
    private static final float DEFAULT_RADIUS = 5;
    private static final float DEFAULT_STROKE = 1;
    private Context mContext;
    // 密码个数
    private int passwordNum = DEFAULT_PASS_NUM;
    // 密码半径
    private int passPointRadius;
    // 密码颜色
    private int passPointColor = DEFAULT_COLOR;
    // 密码框颜色
    private int passStrokeColor = DEFAULT_COLOR;
    // 密码框尺寸
    private int passStroke;
    // 密码框圆角
    private int passCorner;

    private Paint mPaint;
    // 单个密码框的宽度
    private float passOneWidth;


    public PasswordView(Context context) {
        super(context);
    }

    public PasswordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initAttrs(attrs);
    }

    public PasswordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.PasswordView);
        if (typedArray != null) {
            passwordNum = typedArray.getInt(R.styleable.PasswordView_pass_num, passwordNum);
            passPointRadius = (int) typedArray.getDimension(
                    R.styleable.PasswordView_pass_point_radius,
                    dp2px(DEFAULT_RADIUS));
            passPointColor = typedArray.getColor(R.styleable.PasswordView_pass_point_color, passPointColor);
            passStroke = (int) typedArray.getDimension(R.styleable.PasswordView_pass_stroke,
                    dp2px(DEFAULT_STROKE));
            passStrokeColor = typedArray.getColor(R.styleable.PasswordView_pass_stroke_color, passStrokeColor);
            passCorner = (int) typedArray.getDimension(R.styleable.PasswordView_pass_corner,
                    dp2px(DEFAULT_RADIUS));
            typedArray.recycle();
        }

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        // 限制输入位数
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(passwordNum)});
        // 设置输入模式 为数字密码模式
        setInputType(EditorInfo.TYPE_CLASS_NUMBER | EditorInfo.TYPE_NUMBER_VARIATION_PASSWORD);
        // 设置不显示光标
        setCursorVisible(false);
        // 去掉EditText带的下划线
        setBackground(null);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);

        // 计算密码框的宽度
        float passViewWidth = getWidth() - (passwordNum - 1) * passStroke;
        passOneWidth = passViewWidth / passwordNum;
        // 绘制边框
        drawStroke(canvas);
        // 绘制分隔线
        drawDivider(canvas);
        // 绘制密码
        drawPassword(canvas);
    }

    private void drawPassword(Canvas canvas) {
        mPaint.setColor(passPointColor);
        mPaint.setStyle(Paint.Style.FILL);

        Editable editable = getText();
        if (editable == null) return;
        String password = editable.toString().trim();
        int passLen = password.length();
        for (int i = 0; i < passLen; i++) {
            int cenX = (int) (passStroke + passOneWidth * i + passOneWidth / 2 + passStroke * i);
            canvas.drawCircle(cenX, getHeight() / 2, passPointRadius, mPaint);
        }

        // 回调，密码输入完成需要自动做后续操作
        if (onPassChangedListener != null && password.length() == passwordNum) {
            onPassChangedListener.onFullPassword(password);
        }
    }

    private void drawDivider(Canvas canvas) {
        mPaint.setColor(passStrokeColor);
        mPaint.setStyle(Paint.Style.FILL);

        for (int i = 0; i < passwordNum - 1; i++) {
            // 计算每次分隔线的位置
            int startX = (int) (i * passStroke + (i + 1) * passOneWidth + passStroke);
            canvas.drawLine(startX, passStroke, startX, getHeight() - passStroke, mPaint);
        }
    }


    private void drawStroke(Canvas canvas) {
        mPaint.setColor(passStrokeColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(passStroke);

        RectF rectF = new RectF(passStroke, passStroke, getWidth() - passStroke, getHeight() - passStroke);
        canvas.drawRoundRect(rectF, passCorner, passCorner, mPaint);
    }

    private int dp2px(float dp) {
        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);
    }

    private OnPassChangedListener onPassChangedListener = null;

    public interface OnPassChangedListener {
        void onFullPassword(String password);
    }

    public void setOnPassChangedListener(OnPassChangedListener onPassChangedListener){
        this.onPassChangedListener = onPassChangedListener;
    }
}
