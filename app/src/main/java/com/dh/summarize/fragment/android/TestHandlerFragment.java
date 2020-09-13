package com.dh.summarize.fragment.android;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dh.summarize.R;
import com.dh.summarize.base.BaseFragment;
import com.dh.utils_library.utils.LogUtils;

import org.jetbrains.annotations.NotNull;

/**
 * @author 86351
 * @date 2020/9/3
 * @description
 */
public class TestHandlerFragment extends BaseFragment {
    private static final String TAG = "TestHandlerFragment";
    /**
     * 第一种用法
     */
    private Handler handler1 = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            LogUtils.d(TAG, "我还是收到消息了");
            tvHandler.setText(msg.obj.toString());
        }
    };

    /**
     * 第二种用法，推荐使用第二种用法
     */
    private Handler handler2 = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            tvHandler.setText(msg.obj.toString());
            return false;
        }
    })/*{
        @Override
        public void handleMessage(@NonNull Message msg) {
            LogUtils.d(TAG, "走了callback，还是走到了这里");
            super.handleMessage(msg);
        }
    }*/;

    private Handler handler3 = new Handler();

    private TextView tvHandler;

    public static TestHandlerFragment newInstance() {
        return new TestHandlerFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.test_handler_fragment_layout;
    }

    @Override
    public void initViews(@NotNull View view) {
        tvHandler = view.findViewById(R.id.tvHandler);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(5000);
                Message message = Message.obtain();
                message.obj = "Handler赋值";
                message.what = 100;
                //handler1.sendMessage(message);
                if (handler2 != null) handler1.sendMessage(message);
                // if (handler2 != null) handler2.sendMessageDelayed(message, 5000);

                /*handler3.post(new Runnable() {
                    @Override
                    public void run() {
                        tvHandler.setText("Handler赋值");
                    }
                });*/
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler2.removeCallbacksAndMessages(null);

        /*handler2.removeMessages(100);
        handler2 = null;*/
    }
}
