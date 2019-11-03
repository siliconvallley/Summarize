package com.dh.summarize.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.dh.http.uiils.LogUtils;

/**
 * @author 86351
 * @date 2019/11/3
 * @description https://www.gcssloop.com/customview/multi-touch
 */
public class MotionEventView extends View {
    private static final String TAG = "MotionEventView";

    public MotionEventView(Context context) {
        super(context);
    }

    public MotionEventView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MotionEventView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * MotionEvent中用前8位来表示index，后8位来表示action事件
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // 获取事件类型
        // int action = event.getAction() & MotionEvent.ACTION_MASK;
        // 获取index
        //int index = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;


        int index = event.getActionIndex();

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.INSTANCE.e(TAG, "第一个手指按下");
                break;
            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < event.getPointerCount(); i++) {
                    LogUtils.INSTANCE.e(TAG, "pointerIndex:" + i + "==>pointerId:" + event.getPointerId(i));
                }
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.INSTANCE.e(TAG, "第一个手指移开");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                LogUtils.INSTANCE.e(TAG, "第" + (index + 1) + "个手指按下");
                break;
            case MotionEvent.ACTION_POINTER_UP:
                LogUtils.INSTANCE.e(TAG, "第" + (index + 1) + "个手指移开");
                break;

        }

        return true;
    }
}
