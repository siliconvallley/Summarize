package com.dh.summarize.base

import android.os.StrictMode
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.dh.summarize.BuildConfig
import com.dh.summarize.jetpack.ApplicationObserver
import com.dh.summarize.utils.screen.UIUtils
import leakcanary.AppWatcher
import leakcanary.LeakCanary

/**
 * 配置APP的严苛模式，提升应用的性能
 * https://blog.csdn.net/u012317510/article/details/78747612
 */
class BaseApplication : MultiDexApplication() {
    companion object {
        // 注解是为了保证，在kotlin和JAVA互调的过程中，保证调用都是BaseApplication.instance
        // 这样写之后就会被编译成public static BaseApplication instance
        @JvmStatic
        lateinit var instance: BaseApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        //initStrict()
        init()
    }

    private fun initStrict() {
        // 3、设置Android的严苛模式，有两种策略
        // ThreadPolicy：主要可以检测主线程中的一些耗时操作
        // VmPolicy：主要可以检测一些对象的泄漏。
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    //.detectDiskReads()
                    //.detectDiskWrites()
                    //.detectNetwork()
                    //.detectUnbufferedIo()
                    //.detectCustomSlowCalls()
                    //.detectResourceMismatches()
                    .detectAll() // 开启支持的所有检测
                    .penaltyLog()
                    .penaltyDialog()
                    .penaltyDeath()
                    .build()
            )
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .penaltyDeath()
                    .build()
            )
        }
    }

    private fun init() {
        // 1、初始化内存泄露工具
        AppWatcher.objectWatcher.watch(instance)
        // 自定义检测对象的保留
        /*AppWatcher.config = AppWatcher.config.copy(watchFragments = false)
        AppWatcher.config.copy(watchFragmentViews = false)
        AppWatcher.config.copy(watchDurationMillis = 2)
        AppWatcher.config.copy(watchActivities = false)*/
        // 自定义堆转储和分析
        // LeakCanary.config = LeakCanary.config.copy(retainedVisibleThreshold = 3)
        // 2、分包
        MultiDex.install(instance)
        // 3、初始化屏幕适配工具
        UIUtils.getInstance(instance)
        // 3、监听Application的声明周期
        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationObserver())
    }
}