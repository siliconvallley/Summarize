package com.dh.summarize.fragment.android;

import android.view.View;

import com.dh.summarize.R;
import com.dh.summarize.base.BaseFragment;

import org.jetbrains.annotations.NotNull;

/**
 * @author 86351
 * @date 2019/10/5
 * @description
 */
public class PaintFragment extends BaseFragment {

    public static PaintFragment getInstance() {
        return new PaintFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.paint_fragment_layout;
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
