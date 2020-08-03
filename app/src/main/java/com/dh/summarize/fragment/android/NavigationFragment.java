package com.dh.summarize.fragment.android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.dh.summarize.R;
import com.dh.summarize.base.BaseFragment;

import org.jetbrains.annotations.NotNull;

/**
 * @author 86351
 * @date 2020/7/21
 * @description NavigationFragment
 */
public class NavigationFragment extends BaseFragment implements View.OnClickListener {

    private Button btToSecond;

    public static NavigationFragment newInstance() {
        NavigationFragment fragment = new NavigationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initParams() {
        super.initParams();
        if (getArguments() != null) {

        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    public void initViews(@NotNull View view) {
        btToSecond = view.findViewById(R.id.btToSecond);
    }

    @Override
    public void initListener() {
        btToSecond.setOnClickListener(this);
        // 第二种方式、跳转到NavigationSecondFragment
        /*btToSecond.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_navigationFragment_to_navigationSecondFragment)
        );*/
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        // 第一种方式、跳转到NavigationSecondFragment
        //Navigation.findNavController(v).navigate(R.id.action_navigationFragment_to_navigationSecondFragment);

        // 通过代码方式设置Fragment切换动画
        NavOptions navOptions = new NavOptions.Builder()
                .setEnterAnim(R.anim.enter_left)
                .setExitAnim(R.anim.exit_left)
                .setPopEnterAnim(R.anim.enter_left)
                .setPopExitAnim(R.anim.exit_left)
                .build();

        // 1、通过Bundle方式传递值
        /*Bundle bundle = new Bundle();
        bundle.putString("key", "传统传值");*/

        // 2、使用safe-args插件传递值
        Bundle bundle = new NavigationFragmentArgs.Builder()
                .setUserName("张三")
                .setAge(20).build().toBundle();

        Navigation.findNavController(v).navigate(
                R.id.action_navigationFragment_to_navigationSecondFragment,
                bundle
        );


    }

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_navigation, container, false);
    }*/
}