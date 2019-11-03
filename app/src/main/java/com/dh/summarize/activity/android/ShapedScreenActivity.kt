package com.dh.summarize.activity.android

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.DisplayCutout
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintLayout
import com.dh.summarize.R
import com.dh.summarize.utils.screen.ViewCalculateUtils
import kotlinx.android.synthetic.main.activity_shaped_screen.*

/**
 * 刘海屏适配，判断是否有刘海屏放在onAttachedToWindow中去，因为View没有绘制出来的时候，会检测不到
 */
class ShapedScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1.设置全屏
        // 取消标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // 手机厂商：华为，小米，OPPO等
        // 1.判断手机厂商，
        // 2，判断手机是否有刘海，
        // 3，设置是否让内容区域延伸进刘海
        // 4，设置控件是否避开刘海区域
        // 5，获取刘海的高度

        // 2.判断是否是刘海屏
        if (hasDisplayCutout(window)) {
            // 将内容区域延伸进刘海
            val attributes = window?.attributes
            /*
             * LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT 全屏模式，内容下移，非全屏不受影响
             * LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES 允许内容去延伸进刘海区
             * LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER 不允许内容延伸进刘海区
             */
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                attributes?.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
                window?.attributes = attributes
            }


            //3.设置成沉浸式
            val flags =
                View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            var systemUiVisibility = window?.decorView?.systemUiVisibility
            systemUiVisibility = systemUiVisibility?.or(flags)
            window?.decorView?.systemUiVisibility = systemUiVisibility ?: 0
        }

        setContentView(R.layout.activity_shaped_screen)

        // 4，设置控件是否避开刘海区域
        val layoutParams: ConstraintLayout.LayoutParams =
            tvShape.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.topMargin = getStatusBarHeight()
        tvShape.layoutParams = layoutParams

        ViewCalculateUtils.setTextSize(tvScreen,50)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        //hasDisplayCutout(window)
    }

    /**
     * 判断是否是刘海屏
     */
    private fun hasDisplayCutout(window: Window?): Boolean {
        val displayCutout: DisplayCutout?
        val decorView = window?.decorView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val rootWindowInsets = decorView?.rootWindowInsets
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && rootWindowInsets != null) {
                displayCutout = rootWindowInsets.displayCutout
                if (displayCutout != null) {
                    // 判断刘海屏区域数量，判断刘海区域是否高度大于0
                    if (displayCutout.boundingRects.size > 0 && displayCutout.safeInsetBottom > 0) {
                        return true
                    }
                }
            }
        }
        return false
    }

    /**
     * 获取状态栏高度（通常情况下，刘海的高度等于状态栏的高度）
     */
    private fun getStatusBarHeight(): Int {
        val resId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resId > 0) {
            return resources.getDimensionPixelSize(resId)
        }
        return 0
    }
}
