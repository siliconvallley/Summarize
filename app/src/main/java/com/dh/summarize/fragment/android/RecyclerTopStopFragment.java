package com.dh.summarize.fragment.android;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dh.summarize.R;
import com.dh.summarize.activity.android.AndroidActivity;
import com.dh.summarize.adapter.RecyclerTopStopAdapter;
import com.dh.summarize.base.BaseFragment;
import com.dh.summarize.utils.StatusBarHelper;

import org.jetbrains.annotations.NotNull;

/**
 * @author 86351
 * @date 2019/11/9
 * @description RecyclerTopStopFragment
 */
public class RecyclerTopStopFragment extends BaseFragment {
    private RecyclerView rvList;
    private RelativeLayout rlTop;
    private ImageView ivAvater;
    private TextView tvNickname;
    private RecyclerTopStopAdapter adapter;

    private int topStopHeight;
    private LinearLayoutManager layoutManager;
    private int mCurrentItem;


    public static RecyclerTopStopFragment getInstance() {
        return new RecyclerTopStopFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.recycler_top_stop_fragment;
    }

    @Override
    public void initViews(@NotNull View view) {
        AndroidActivity androidActivity = (AndroidActivity) mActivity;
        androidActivity.getToolBar().setVisibility(View.GONE);

        rvList = view.findViewById(R.id.rvList);
        rlTop = view.findViewById(R.id.rlTop);
        ivAvater = view.findViewById(R.id.ivAvatar);
        tvNickname = view.findViewById(R.id.tvNickname);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        setAdapter();

        rvList.addOnScrollListener(onScrollListener);
        updateTop();
    }

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            // 获取顶部悬停条的高度
            topStopHeight = rlTop.getHeight();
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            // 更新悬停条的位置
            // 当前item的下一个Item
            View view = layoutManager.findViewByPosition(mCurrentItem + 1);
            if (view != null) {
                // 判断当前可见Item的下一个Item距离顶部的距离，然后改变View的位置
                if (view.getTop() <= topStopHeight) {
                    //需要对悬浮条进行移动
                    //rlTop.setY(-(topStopHeight - view.getTop()));
                    rlTop.setY(view.getTop() - topStopHeight);
                } else {
                    //保持在原来的位置
                    rlTop.setY(0);
                }
            }

            if (mCurrentItem != layoutManager.findFirstVisibleItemPosition()) {
                mCurrentItem = layoutManager.findFirstVisibleItemPosition();
                updateTop();
            }
        }
    };

    private void updateTop() {
        Glide.with(mActivity)
                .load(getAvatarResId(mCurrentItem))
                .centerCrop()
                .into(ivAvater);
        tvNickname.setText("李白 " + mCurrentItem);
    }

    private void setAdapter() {
        layoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        rvList.setLayoutManager(layoutManager);
        adapter = new RecyclerTopStopAdapter();
        rvList.setAdapter(adapter);
        rvList.setHasFixedSize(true);
    }

    private int getAvatarResId(int position) {
        switch (position % 4) {
            case 0:
                return R.drawable.avatar1;
            case 1:
                return R.drawable.avatar2;
            case 2:
                return R.drawable.avatar3;
            case 3:
                return R.drawable.avatar4;
        }
        return 0;
    }
}
