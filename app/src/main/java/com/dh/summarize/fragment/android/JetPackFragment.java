package com.dh.summarize.fragment.android;

import android.app.AppComponentFactory;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.dh.summarize.R;
import com.dh.summarize.activity.android.LifeCycleActivity;
import com.dh.summarize.activity.android.NavigationActivity;
import com.dh.summarize.base.BaseFragment;

import org.jetbrains.annotations.NotNull;

/**
 * @author 86351
 * @date 2020/7/19
 * @description
 */
public class JetPackFragment extends BaseFragment implements View.OnClickListener {

    private Button btLifeCycle;
    private Button btNavigation;

    public static JetPackFragment getInstance() {
        return new JetPackFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.jetpack_fragment_layout;
    }

    @Override
    public void initViews(@NotNull View view) {
        btLifeCycle = view.findViewById(R.id.btLifeCycle);
        btNavigation = view.findViewById(R.id.btNavigation);
    }

    @Override
    public void initListener() {
        btLifeCycle.setOnClickListener(this);
        btNavigation.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btLifeCycle:
                startActivity(mActivity, LifeCycleActivity.class);
                break;
            case R.id.btNavigation:
                startActivity(mActivity, NavigationActivity.class);
                break;
        }
    }
}
