package com.dh.summarize.activity.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dh.summarize.R
import com.dh.summarize.utils.StatusBarHelper

/**
 * 郭霖的沉浸式博客：
 * https://blog.csdn.net/guolin_blog/article/details/51763825
 * OPPO开发者平台也有具体的沉浸式的适配
 */
class TestAndroidActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_android)

        StatusBarHelper.translucentAll(this)
    }
}
