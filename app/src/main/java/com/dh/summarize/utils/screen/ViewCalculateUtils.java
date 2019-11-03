package com.dh.summarize.utils.screen;

import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;

/**
 * @author 86351
 * @date 2019/11/3
 * @description
 */
public class ViewCalculateUtils {

    /**
     * 设置字体大小
     *
     * @param textView
     * @param size
     */
    public static void setTextSize(TextView textView, int size) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, UIUtils.getInstance().getHeight(size));
    }


    /**
     * @param view
     * @param width
     * @param height
     * @param lefMargin
     * @param topMargin
     * @param rightMargin
     * @param bottomMargin
     * @param asWidth      true 不改变宽高的缩放比
     */
    public static void setViewRelativeLayoutParam(View view, int width, int height, int lefMargin, int topMargin,
                                                  int rightMargin, int bottomMargin, @Nullable boolean asWidth) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        if (layoutParams == null) return;
        if (width != RelativeLayout.LayoutParams.MATCH_PARENT
                && width != RelativeLayout.LayoutParams.WRAP_CONTENT) {
            layoutParams.width = UIUtils.getInstance().getWidth(width);
        } else {
            layoutParams.width = width;
        }

        if (height != RelativeLayout.LayoutParams.MATCH_PARENT
                && height != RelativeLayout.LayoutParams.WRAP_CONTENT) {
            layoutParams.height = asWidth ? UIUtils.getInstance().getWidth(height) : UIUtils.getInstance().getHeight(height);
        } else {
            layoutParams.height = height;
        }
        layoutParams.topMargin = asWidth ? UIUtils.getInstance().getWidth(topMargin) : UIUtils.getInstance().getHeight(topMargin);
        layoutParams.bottomMargin = asWidth ? UIUtils.getInstance().getWidth(bottomMargin) : UIUtils.getInstance().getHeight(bottomMargin);
        layoutParams.leftMargin = UIUtils.getInstance().getWidth(lefMargin);
        layoutParams.rightMargin = UIUtils.getInstance().getWidth(rightMargin);
        view.setLayoutParams(layoutParams);
    }

    /**
     * 设置view的内边距
     *
     * @param view
     * @param topPadding
     * @param bottomPadding
     * @param leftPadding
     * @param rightPadding
     */
    public static void setViewPadding(View view, int topPadding, int bottomPadding, int leftPadding, int rightPadding) {
        view.setPadding(UIUtils.getInstance().getWidth(leftPadding),
                UIUtils.getInstance().getHeight(topPadding),
                UIUtils.getInstance().getWidth(rightPadding),
                UIUtils.getInstance().getHeight(bottomPadding));
    }

    /**
     * @param view
     * @param width
     * @param height
     * @param topMargin
     * @param bottomMargin
     * @param lefMargin
     * @param rightMargin
     */
    public static void setViewFrameLayoutParam(View view, int width, int height, int topMargin, int bottomMargin, int lefMargin,
                                               int rightMargin, @Nullable boolean asWidth) {

        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        if (width != RelativeLayout.LayoutParams.MATCH_PARENT
                && width != RelativeLayout.LayoutParams.WRAP_CONTENT) {
            layoutParams.width = UIUtils.getInstance().getWidth(width);
        } else {
            layoutParams.width = width;
        }
        if (height != RelativeLayout.LayoutParams.MATCH_PARENT
                && height != RelativeLayout.LayoutParams.WRAP_CONTENT) {
            layoutParams.height = asWidth ? UIUtils.getInstance().getWidth(height) : UIUtils.getInstance().getHeight(height);
        } else {
            layoutParams.height = height;
        }

        layoutParams.topMargin = asWidth ? UIUtils.getInstance().getWidth(topMargin) : UIUtils.getInstance().getHeight(topMargin);
        layoutParams.bottomMargin = asWidth ? UIUtils.getInstance().getWidth(bottomMargin) : UIUtils.getInstance().getHeight(bottomMargin);
        layoutParams.leftMargin = UIUtils.getInstance().getWidth(lefMargin);
        layoutParams.rightMargin = UIUtils.getInstance().getWidth(rightMargin);
        view.setLayoutParams(layoutParams);
    }


    /**
     * @param view
     * @param width
     * @param height
     * @param topMargin
     * @param bottomMargin
     * @param lefMargin
     * @param rightMargin
     * @param asWidth
     */
    public static void setViewLinearLayoutParam(View view, int width, int height, int topMargin, int bottomMargin, int lefMargin,
                                                int rightMargin, @Nullable boolean asWidth) {

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (width != RelativeLayout.LayoutParams.MATCH_PARENT && width != RelativeLayout.LayoutParams.WRAP_CONTENT) {
            layoutParams.width = UIUtils.getInstance().getWidth(width);
        } else {
            layoutParams.width = width;
        }
        if (height != RelativeLayout.LayoutParams.MATCH_PARENT && height != RelativeLayout.LayoutParams.WRAP_CONTENT) {
            layoutParams.height = asWidth ? UIUtils.getInstance().getWidth(height) : UIUtils.getInstance().getHeight(height);
        } else {
            layoutParams.height = height;
        }

        layoutParams.topMargin = asWidth ? UIUtils.getInstance().getWidth(topMargin) : UIUtils.getInstance().getHeight(topMargin);
        layoutParams.bottomMargin = asWidth ? UIUtils.getInstance().getWidth(bottomMargin) : UIUtils.getInstance().getHeight(bottomMargin);
        layoutParams.leftMargin = UIUtils.getInstance().getWidth(lefMargin);
        layoutParams.rightMargin = UIUtils.getInstance().getWidth(rightMargin);
        view.setLayoutParams(layoutParams);
    }

    /**
     * @param view
     * @param width
     * @param height
     * @param asWidth
     */
    public static void setViewGroupLayoutParam(View view, int width, int height, @Nullable boolean asWidth) {

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (width != RelativeLayout.LayoutParams.MATCH_PARENT && width != RelativeLayout.LayoutParams.WRAP_CONTENT && width != RelativeLayout.LayoutParams.FILL_PARENT) {
            layoutParams.width = UIUtils.getInstance().getWidth(width);
        } else {
            layoutParams.width = width;
        }
        if (height != RelativeLayout.LayoutParams.MATCH_PARENT && height != RelativeLayout.LayoutParams.WRAP_CONTENT && height != RelativeLayout.LayoutParams.FILL_PARENT) {
            layoutParams.height = asWidth ? UIUtils.getInstance().getWidth(height) : UIUtils.getInstance().getHeight(height);
        } else {
            layoutParams.height = height;
        }
        view.setLayoutParams(layoutParams);
    }
}
