package com.dh.summarize.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;

/**
 * @author 86351
 * @date 2019/10/29
 * @description
 */
public class DensityUtils {
    /**
     * 设计稿宽度（dp）
     */
    private static final float DEFAULT_WIDTH = 360;
    // 屏幕密度
    private static float appDensity;
    // 字体缩放比，默认就是appDensity
    private static float appScaleDensity;


    public static void setCustomDensity(Application application, Activity activity) {
        DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();

        if (appDensity == 0) {
            // 初始化值
            appDensity = displayMetrics.density;
            appScaleDensity = displayMetrics.scaledDensity;

            // 由于我们在系统设置中切换了文字大小，需要改变应用本身的文字大小
            application.registerComponentCallbacks(new ComponentCallbacks() {
                // 监听系统配置切换
                @Override
                public void onConfigurationChanged(@NonNull Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        // 重新获取scaleDensity
                        appScaleDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        // 计算所有的目标值
        float targetDensity = displayMetrics.widthPixels / DEFAULT_WIDTH;
        float targetScaleDensity = targetDensity * (appScaleDensity / appDensity);
        // density = dpi / 160
        int targetDensityDpi = (int) (targetDensity * 160);

        displayMetrics.density = targetDensity;
        displayMetrics.scaledDensity = targetScaleDensity;
        displayMetrics.densityDpi = targetDensityDpi;

        // 将Activity的Density，ScaleDensity，DensityDpi
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        dm.density = targetDensity;
        dm.scaledDensity = targetScaleDensity;
        dm.densityDpi = targetDensityDpi;
    }
}
