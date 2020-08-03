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
public class LifeCycleTest implements LifecycleObserver {
    private static final String TAG = "LifeCycleTest";
    private OnLifeCycleListener onLifeCycleListener;

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void start() {
        LogUtils.d(TAG, "onResume");
        if (onLifeCycleListener != null) {
            onLifeCycleListener.onLifeCycle("LifeCycleTest");
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void stop() {
        LogUtils.d(TAG, "onPause");
    }

    public interface OnLifeCycleListener {
        void onLifeCycle(String data);
    }

    public void setOnLifeCycleListener(OnLifeCycleListener onLifeCycleListener) {
        this.onLifeCycleListener = onLifeCycleListener;
    }
}
