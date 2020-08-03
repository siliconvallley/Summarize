package com.dh.summarize.activity.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dh.summarize.R;
import com.dh.summarize.base.BaseActivity;
import com.dh.summarize.jetpack.LifeCycleTest;
import com.dh.summarize.service.MyLifeCycleService;

public class LifeCycleActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvContent;
    private Button btStart;
    private Button btStop;

    @Override
    public int getLayoutId() {
        return R.layout.activity_life_cycle;
    }

    @Override
    public void initViews() {
        tvContent = findViewById(R.id.tvContent);
        btStart = findViewById(R.id.btStart);
        btStop = findViewById(R.id.btStop);
    }

    @Override
    public void initListener() {
        tvContent.setOnClickListener(this);
        btStart.setOnClickListener(this);
        btStop.setOnClickListener(this);
    }

    @Override
    public void initData() {
        LifeCycleTest lifeCycleTest = new LifeCycleTest();
        lifeCycleTest.setOnLifeCycleListener(lifeCycleListener);
        getLifecycle().addObserver(lifeCycleTest);
    }

    @Override
    public void doBusiness() {

    }

    private LifeCycleTest.OnLifeCycleListener lifeCycleListener = new LifeCycleTest.OnLifeCycleListener() {
        @Override
        public void onLifeCycle(String data) {
            tvContent.setText(data);
        }
    };

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MyLifeCycleService.class);
        switch (v.getId()) {
            case R.id.btStart:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(intent);
                } else {
                    startService(intent);
                }
                break;
            case R.id.btStop:
                stopService(intent);
                break;
        }
    }
}