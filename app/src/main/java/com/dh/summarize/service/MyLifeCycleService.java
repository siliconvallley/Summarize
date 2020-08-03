package com.dh.summarize.service;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleService;

import com.dh.summarize.jetpack.LifeCycleServiceTest;
import com.dh.utils_library.utils.LogUtils;

/**
 * @author 86351
 * @date 2020/7/20
 * @description
 */
public class MyLifeCycleService extends LifecycleService {
    private LifeCycleServiceTest lifeCycleServiceTest;

    public MyLifeCycleService() {
        LogUtils.d(this.getClass().getSimpleName(), "MyLifeCycleService");
        lifeCycleServiceTest = new LifeCycleServiceTest();
        getLifecycle().addObserver(lifeCycleServiceTest);
    }
}
