package com.dh.summarize.fragment.android;

import android.view.View;

import com.dh.summarize.R;
import com.dh.summarize.base.BaseFragment;

import org.jetbrains.annotations.NotNull;

/**
 * @author 86351
 * @date 2019/10/7
 * @description
 */
public class CanvasFragment extends BaseFragment {

    public static CanvasFragment getInstance() {
        return new CanvasFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.canvas_fragment_layout;
    }

    @Override
    public void initViews(@NotNull View view) {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }
}
