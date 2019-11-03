package com.dh.summarize.fragment.android;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.dh.summarize.R;
import com.dh.summarize.base.BaseFragment;

import org.jetbrains.annotations.NotNull;

/**
 * @author 86351
 * @date 2019/10/10
 * @description
 */
public class PathFragment extends BaseFragment {

    public static PathFragment getInstance() {
        return new PathFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.path_fragment_layout;
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
