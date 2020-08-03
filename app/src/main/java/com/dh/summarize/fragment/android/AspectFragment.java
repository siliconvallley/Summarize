package com.dh.summarize.fragment.android;

import android.view.View;
import android.widget.Button;

import com.dh.summarize.R;
import com.dh.summarize.annotation.ClickBehavior;
import com.dh.summarize.annotation.LoginCheck;
import com.dh.summarize.base.BaseFragment;
import com.dh.utils_library.utils.LogUtils;

import org.jetbrains.annotations.NotNull;

/**
 * @author 86351
 * @date 2020/8/3
 * @description
 */
public class AspectFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "Aspect_Fragment";
    private Button btLogin;
    private Button btIntegral;
    private Button btInfo;

    public static AspectFragment newInstance() {
        return new AspectFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.aspect_fragment_layout;
    }

    @Override
    public void initViews(@NotNull View view) {
        btLogin = view.findViewById(R.id.btLogin);
        btIntegral = view.findViewById(R.id.btIntegral);
        btInfo = view.findViewById(R.id.btInfo);
    }

    @Override
    public void initListener() {
        btLogin.setOnClickListener(this);
        btIntegral.setOnClickListener(this);
        btInfo.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btLogin:
                login();
                break;
            case R.id.btIntegral:
                integral();
                break;
            case R.id.btInfo:
                info();
                break;
        }
    }

    @ClickBehavior("登录")
    public void login() {
        LogUtils.d(TAG, "正在登录......");
    }

    @ClickBehavior("我的积分")
    @LoginCheck
    public void integral() {
        LogUtils.d(TAG, "跳转到我的积分页......");
    }

    @ClickBehavior("我的信息")
    @LoginCheck
    public void info() {
        LogUtils.d(TAG, "跳转到我的信息页......");
    }


}
