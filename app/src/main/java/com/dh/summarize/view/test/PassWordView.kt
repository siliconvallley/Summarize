package com.dh.summarize.view.test

import android.content.Context
import android.text.InputFilter
import android.text.TextUtils
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import android.graphics.*
import androidx.appcompat.widget.AppCompatEditText
import com.dh.summarize.R


/**
 * @author 86351
 * @date 2019/11/3
 * @description PassWordView 密码输入框
 */
class PassWordView : AppCompatEditText {
    private var mContext: Context
    private var passNum: Int = 0
    private var passPointRadius: Int = 0
    private var passPointColor: Int = Color.parseColor("#000000")
    private var dividerSize: Int = 0
    private var dividerColor: Int = Color.parseColor("#ebe9e9")
    private var strokeSize: Int = 0
    private var strokeColor: Int = dividerColor
    private var bgCorner: Int = 0
    private lateinit var mPaint: Paint
    // 单个密码的宽度
    private var passOneWidth: Int = 0
    private var onPasswordListener: OnPasswordListener? = null

    constructor(context: Context) : super(context) {
        this.mContext = context
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.mContext = context
        initAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        this.mContext = context
        initAttrs(attrs)
    }

    private fun initAttrs(attrs: AttributeSet) {
        val typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.PassWordView)
        if (typedArray != null) {
            passNum = typedArray.getInt(R.styleable.PassWordView_password_number, 6)
            passPointRadius = typedArray.getDimension(
                R.styleable.PassWordView_password_point_radius,
                dp2px(4f).toFloat()
            ).toInt()
            passPointColor =
                typedArray.getColor(R.styleable.PassWordView_password_point_color, passPointColor)
            dividerSize = typedArray.getDimension(
                R.styleable.PassWordView_password_divider_size,
                dp2px(1f).toFloat()
            ).toInt()
            dividerColor =
                typedArray.getColor(R.styleable.PassWordView_password_divider_color, dividerColor)
            strokeSize = typedArray.getDimension(
                R.styleable.PassWordView_password_stroke_size,
                dp2px(1f).toFloat()
            ).toInt()
            strokeColor =
                typedArray.getColor(R.styleable.PassWordView_password_stroke_color, strokeColor)
            bgCorner = typedArray.getDimension(
                R.styleable.PassWordView_password_corner,
                0f
            ).toInt()
            typedArray.recycle()
        }

        initPaint()
        // 限制输入位数
        filters = arrayOf(InputFilter.LengthFilter(passNum))
        // 设置输入模式 为数字密码模式
        inputType = EditorInfo.TYPE_CLASS_NUMBER or EditorInfo.TYPE_NUMBER_VARIATION_PASSWORD
        // 设置不显示光标
        isCursorVisible = false
        // 去掉EditText带的下划线
        background = null
    }

    private fun initPaint() {
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.isDither = true
    }

    override fun onDraw(canvas: Canvas?) {
        //super.onDraw(canvas)
        // 减去分隔线宽度
        val passViewWidth = width - (passNum - 1) * dividerSize
        // 获取单个密码宽度
        passOneWidth = passViewWidth / passNum
        drawStroke(canvas)
        drawPass(canvas)
        drawDivider(canvas)
    }

    /**
     * 绘制分隔线
     */
    private fun drawDivider(canvas: Canvas?) {
        mPaint.color = dividerColor
        mPaint.style = Paint.Style.FILL
        for (i in 0 until passNum - 1) {
            val startX: Int = (i + 1) * dividerSize + (i + 1) * passOneWidth + strokeSize
            canvas?.drawLine(
                startX.toFloat(), strokeSize.toFloat(), startX.toFloat(),
                (height - strokeSize).toFloat(), mPaint
            )
        }
    }

    /**
     * 绘制密码
     */
    private fun drawPass(canvas: Canvas?) {
        val password = text.toString().trim()
        val length = password.length
        mPaint.color = passPointColor
        mPaint.style = Paint.Style.FILL
        for (i in 0 until length) {
            val centerX = i * dividerSize + i * passOneWidth + passOneWidth / 2 + strokeSize
            canvas?.drawCircle(
                centerX.toFloat(),
                (height / 2).toFloat(),
                passPointRadius.toFloat(),
                mPaint
            )
        }

        if (onPasswordListener != null && password.length == passNum) {
            onPasswordListener?.onFullPassword(password)
        }
    }

    /**
     * 绘制边框
     */
    private fun drawStroke(canvas: Canvas?) {
        mPaint.color = strokeColor
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = strokeSize.toFloat()
        val rectF: RectF = RectF(
            strokeSize.toFloat(),
            strokeSize.toFloat(),
            (width - strokeSize).toFloat(),
            (height - strokeSize).toFloat()
        )
        if (bgCorner == 0) {
            canvas?.drawRect(rectF, mPaint)
        } else {
            canvas?.drawRoundRect(rectF, bgCorner.toFloat(), bgCorner.toFloat(), mPaint)
        }
    }

    /**
     * 添加密码
     */
    fun addPassword(number: Int) {
        val password = text.toString().trim() + number
        if (password.length > passNum) {
            return
        }
        setText(password)
        /*if (onPasswordListener != null && password.length == passNum) {
            onPasswordListener?.onFullPassword(password)
        }*/
    }

    /**
     * 清除内容
     */
    fun clear() {
        text?.clear()
    }

    /**
     * 删除密码
     */
    fun delPassword() {
        var password = text.toString().trim()
        if (TextUtils.isEmpty(password)) {
            return
        }
        password = password.substring(0, password.length - 1)
        setText(password)
    }

    interface OnPasswordListener {
        fun onFullPassword(password: String)
    }

    fun setOnPasswordListener(onPasswordListener: OnPasswordListener) {
        this.onPasswordListener = onPasswordListener
    }

    private fun dp2px(dp: Float): Int {
        val density = mContext.resources.displayMetrics.density
        return (dp * density + 0.5f).toInt()
    }
}
