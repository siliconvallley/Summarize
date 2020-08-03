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
public class ApplicationObserver implements LifecycleObserver {
    private static final String TAG = "ApplicationObserver";

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(){
        LogUtils.d(TAG, "onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(){
        LogUtils.d(TAG, "onStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(){
        LogUtils.d(TAG, "onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(){
        LogUtils.d(TAG, "onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(){
        LogUtils.d(TAG, "onStop");
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(){
        LogUtils.d(TAG, "onDestroy");
    }
}
