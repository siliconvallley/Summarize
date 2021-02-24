package com.dh.summarize.viewmodel;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author 86351
 * @date 2021/2/17
 * @description
 */
public class TimerViewModel extends ViewModel {
    private static final int TIMER_TAG = 100;
    private MutableLiveData<Integer> mCurrentSecond;
    private int mCurrentValue;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (msg.what == TIMER_TAG) {
                mCurrentValue++;
                mCurrentSecond.setValue(mCurrentValue);
                mHandler.sendEmptyMessageDelayed(TIMER_TAG, 1000);
            }
            return false;
        }
    });

    public MutableLiveData<Integer> getCurrentSecond() {
        if (mCurrentSecond == null) {
            mCurrentSecond = new MutableLiveData<>();
        }
        return mCurrentSecond;
    }

    public void startTiming() {
        mCurrentValue = 0;
        mCurrentSecond.setValue(mCurrentValue);
        mHandler.sendEmptyMessageDelayed(TIMER_TAG, 1000);
    }

    public void resetTime() {
        mCurrentValue = 0;
        mCurrentSecond.setValue(mCurrentValue);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mHandler != null) {
            mHandler.removeMessages(TIMER_TAG);
            mHandler = null;
        }
    }
}
