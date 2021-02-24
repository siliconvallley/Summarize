package com.dh.summarize.activity.android;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.dh.summarize.R;
import com.dh.summarize.base.BaseActivity;
import com.dh.summarize.viewmodel.TimerViewModel;

public class ViewModelActivity extends BaseActivity implements View.OnClickListener {

    private Button btReset;
    private TextView tvTime;
    private TimerViewModel mTimerViewModel;
    private MutableLiveData<Integer> mSecondLiveData;

    @Override
    public int getLayoutId() {
        return R.layout.activity_view_model;
    }

    @Override
    public void initViews() {
        btReset = findViewById(R.id.btReset);
        tvTime = findViewById(R.id.tvTime);
    }

    @Override
    public void initListener() {
        btReset.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mTimerViewModel = new ViewModelProvider(this).get(TimerViewModel.class);
        mSecondLiveData = mTimerViewModel.getCurrentSecond();

        mSecondLiveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvTime.setText("计时:: " + integer);
            }
        });
        mTimerViewModel.startTiming();
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onClick(View v) {
        mTimerViewModel.resetTime();
    }
}