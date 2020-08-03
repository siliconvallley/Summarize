package com.dh.summarize.jetpack;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.dh.utils_library.utils.LogUtils;

/**
 * @author 86351
 * @date 2020/7/20
 * @description
 */
public class LifeCycleServiceTest implements LifecycleObserver {
    private static final String TAG = "LifeCycleServiceTest";

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void create() {
        LogUtils.d(TAG, "onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destroy() {
        LogUtils.d(TAG, "onDestroy");
    }
}
