package com.dh.summarize.view.screen;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.dh.summarize.utils.ScreenAdapterUtils;

/**
 * @author 86351
 * @date 2019/10/28
 * @description
 * 自定义View的像素适配
 */
public class ScreenAdapterLayout extends LinearLayout {
    /**
     * 是否被测量
     */
    private boolean isMeasure;

    public ScreenAdapterLayout(Context context) {
        super(context);
    }

    public ScreenAdapterLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public ScreenAdapterLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isMeasure) {
            // 获取横向缩放比
            float horizontalScale = ScreenAdapterUtils.getInstance(getContext()).getHorizontalScale();
            // 获取纵向缩放比
            float verticalScale = ScreenAdapterUtils.getInstance(getContext()).getVerticalScale();

            // 重新计算所有子View的尺寸
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                // 获取子View重新进行测量
                View child = getChildAt(i);
                LayoutParams params = (LayoutParams) child.getLayoutParams();
                // 根据宽高的缩放比，重新计算目标View的真实宽高
                params.width = (int) (params.width * horizontalScale);
                // 这样缩放，如果我们设置的宽高是一样的，你会发现在水平和数值方向上的缩放比不一样的时候
                // 控件会被拉伸，不能达到我们想要的正确的相同宽高
                // params.height = (int) (params.height * verticalScale);
                // 如果改成都使用水平的缩放比，就刚好正确
                params.height = (int) (params.height * horizontalScale);

                // 计算目标View四周的间距
                params.leftMargin = (int) (params.leftMargin * horizontalScale);
                params.rightMargin = (int) (params.rightMargin * horizontalScale);
                params.topMargin = (int) (params.topMargin * verticalScale);
                params.bottomMargin = (int) (params.bottomMargin * verticalScale);
            }
        }


        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}

