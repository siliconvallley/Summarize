package com.dh.summarize.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * @author 86351
 * @date 2019/10/28
 * @description
 * 像素适配工具类，计算横向，纵向缩放比例
 */
public class ScreenAdapterUtils {
    //设计稿参考宽高
    private static final float STANDARD_WIDTH = 1080;
    private static final float STANDARD_HEIGHT = 1920;

    //屏幕显示宽高
    private int mScreenWidth;
    private int mScreenHeight;

    private static ScreenAdapterUtils mInstance;

    public static ScreenAdapterUtils getInstance(Context context) {
        if (mInstance == null) {
            synchronized (ScreenAdapterUtils.class) {
                if (mInstance == null) {
                    mInstance = new ScreenAdapterUtils(context.getApplicationContext());
                }
            }
        }
        return mInstance;
    }

    private ScreenAdapterUtils(Context context) {
        if (mScreenHeight == 0 || mScreenWidth == 0) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (wm != null) {
                DisplayMetrics metrics = new DisplayMetrics();
                wm.getDefaultDisplay().getMetrics(metrics);
                if (metrics.widthPixels > metrics.heightPixels) {
                    // 横屏
                    mScreenWidth = metrics.heightPixels;
                    mScreenHeight = metrics.widthPixels;
                } else {
                    // 竖屏
                    mScreenWidth = metrics.widthPixels;
                    mScreenHeight = metrics.heightPixels - getStatusBarHeight(context);
                }
            }
        }
    }


    public int getStatusBarHeight(Context context) {
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            return context.getResources().getDimensionPixelSize(resId);
        }
        return 0;
    }

    //获取水平方向的缩放比例
    public float getHorizontalScale(){
        return mScreenWidth / STANDARD_WIDTH;
    }

    //获取垂直方向的缩放比例
    public float getVerticalScale(){
        return mScreenHeight / STANDARD_HEIGHT;
    }

}
