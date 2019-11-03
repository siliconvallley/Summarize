package com.dh.summarize.view.screen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.dh.summarize.R;

/**
 * @author 86351
 * @date 2019/10/29
 * @description 自定义百分比适配，继承对应的ViewGroup，在使用百分比适配的情况下，不影响ViewGroup本身的属性的使用
 */
public class PercentRelativeLayout extends RelativeLayout {
    public PercentRelativeLayout(Context context) {
        super(context);
    }

    public PercentRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PercentRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 获取父容器的宽高
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);

        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
            // 判断LayoutParams是否是百分比属性
            if (layoutParams instanceof LayoutParams) {
                LayoutParams params = (LayoutParams) layoutParams;
                float widthPercent = params.widthPercent;
                float heightPercent = params.heightPercent;
                float marginLeftPercent = params.marginLeftPercent;
                float marginRightPercent = params.marginRightPercent;
                float marginTopPercent = params.marginTopPercent;
                float marginBottomPercent = params.marginBottomPercent;

                if (widthPercent > 0) {
                    params.width = (int) (parentWidth * widthPercent);
                }
                if (heightPercent > 0) {
                    params.height = (int) (parentHeight * heightPercent);
                }
                if (marginLeftPercent > 0) {
                    params.leftMargin = (int) (parentWidth * marginLeftPercent);
                }
                if (marginRightPercent > 0) {
                    params.rightMargin = (int) (parentWidth * marginRightPercent);
                }
                if (marginTopPercent > 0) {
                    params.topMargin = (int) (parentHeight * marginTopPercent);
                }
                if (marginBottomPercent > 0) {
                    params.bottomMargin = (int) (parentHeight * marginBottomPercent);
                }
            }
        }


        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    public static class LayoutParams extends RelativeLayout.LayoutParams {
        private float widthPercent;
        private float heightPercent;
        private float marginLeftPercent;
        private float marginRightPercent;
        private float marginTopPercent;
        private float marginBottomPercent;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            @SuppressLint("CustomViewStyleable")
            TypedArray typedArray = c.obtainStyledAttributes(attrs, R.styleable.PercentRelativeLayout);
            widthPercent = typedArray.getFloat(R.styleable.PercentRelativeLayout_widthPercent, 0);
            heightPercent = typedArray.getFloat(R.styleable.PercentRelativeLayout_heightPercent, 0);
            marginLeftPercent = typedArray.getFloat(R.styleable.PercentRelativeLayout_marginLeftPercent, 0);
            marginRightPercent = typedArray.getFloat(R.styleable.PercentRelativeLayout_marginRightPercent, 0);
            marginTopPercent = typedArray.getFloat(R.styleable.PercentRelativeLayout_marginTopPercent, 0);
            marginBottomPercent = typedArray.getFloat(R.styleable.PercentRelativeLayout_marginBottomPercent, 0);

            typedArray.recycle();
        }
    }
}
