package com.dh.summarize.fragment.android;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dh.summarize.R;
import com.dh.summarize.base.BaseFragment;

import org.jetbrains.annotations.NotNull;

/**
 * @author 86351
 * @date 2020/7/21
 * @description NavigationSecondFragment
 */
public class NavigationSecondFragment extends BaseFragment {

    private TextView tvContent;
    private String data;
    private StringBuilder builder = new StringBuilder();

    public static NavigationSecondFragment newInstance() {
        NavigationSecondFragment fragment = new NavigationSecondFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initParams() {
        super.initParams();


        Bundle bundle = getArguments();
        if (bundle != null) {
            // 传统Bundle传递值接收值
            // data = bundle.getString("key", "");

            // safe-args传递值接收值
            NavigationFragmentArgs navigationFragmentArgs = NavigationFragmentArgs.fromBundle(bundle);
            String userName = navigationFragmentArgs.getUserName();
            int age = navigationFragmentArgs.getAge();
            builder.append(userName).append("====>").append(age);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_navigation_second;
    }

    @Override
    public void initViews(@NotNull View view) {
        tvContent = view.findViewById(R.id.tvContent);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        // tvContent.setText(data);
        tvContent.setText(builder.toString());
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_second, container, false);
    }*/
}