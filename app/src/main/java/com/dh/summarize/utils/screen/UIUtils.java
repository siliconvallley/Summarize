package com.dh.summarize.utils.screen;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * @author 86351
 * @date 2019/11/3
 * @description
 *
 */
public class UIUtils {
    private static UIUtils instance;

    // 设计稿尺寸，
    private static final float DEFAULT_WIDTH = 1080f;
    private static final float DEFAULT_HEIGHT = 1920;

    private float displayMetricsWidth;
    private float displayMetricsHeight;
    private float mStatusBarHeight;

    private UIUtils(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) return;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (displayMetricsWidth == 0 || displayMetricsHeight == 0) {
            // 忽略掉了导航栏高度
            wm.getDefaultDisplay().getMetrics(displayMetrics);
            // 获取真实的屏幕尺寸
            //wm.getDefaultDisplay().getRealMetrics(displayMetrics);
            mStatusBarHeight = getSystemBarHeight(context);
            // 判断横屏还是竖屏
            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                // 横屏
                displayMetricsWidth = displayMetrics.heightPixels;
                displayMetricsHeight = displayMetrics.widthPixels;
                // 如果不是沉浸式，我们还需要减去状态栏的高度
                // displayMetricsWidth = displayMetrics.heightPixels - mStatusBarHeight;
            } else {
                displayMetricsWidth = displayMetrics.widthPixels;
                displayMetricsHeight = displayMetrics.heightPixels;
            }
        }
    }

    public static UIUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (UIUtils.class) {
                if (instance == null) {
                    instance = new UIUtils(context.getApplicationContext());
                }
            }
        }
        return instance;
    }


    public static UIUtils getInstance() {
        if (instance == null) {
            throw new NullPointerException("UIUtils未被初始化");
        }
        return instance;
    }

    /**
     * 水平缩放比
     *
     * @return
     */
    public float getHorizontalScaleValue() {
        return displayMetricsWidth / DEFAULT_WIDTH;
    }

    /**
     * 竖直缩放比
     *
     * @return
     */
    public float getVerticalScaleValue() {
        return displayMetricsHeight / DEFAULT_HEIGHT;
    }

    /**
     * 获取宽度
     * @param width
     * @return
     */
    public int getWidth(int width) {
        return Math.round((float) width * displayMetricsWidth / DEFAULT_WIDTH);
    }

    /**
     * 获取高度
     * @param height
     * @return
     */
    public int getHeight(int height) {
        return Math.round((float) height * displayMetricsHeight / DEFAULT_HEIGHT);
    }

    /**
     * 用于得到状态框的高度
     */
    public int getSystemBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int height = context.getResources().getDimensionPixelSize(resourceId);
        if (height != -1) {
            return height;
        }
        return getValue(context, "com.android.internal.R$dimen", "system_bar_height", 48);
    }


    private int getValue(Context context, String dimeClass, String system_bar_height, int defaultValue) {
        try {
            Class<?> clazz = Class.forName(dimeClass);
            Object object = clazz.newInstance();
            Field field = clazz.getField(system_bar_height);
            Object obj = field.get(object);
            if (obj == null) return defaultValue;
            int id = Integer.parseInt(obj.toString());
            return context.getResources().getDimensionPixelSize(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
