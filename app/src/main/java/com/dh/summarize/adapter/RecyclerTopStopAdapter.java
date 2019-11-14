package com.dh.summarize.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dh.summarize.R;


public class RecyclerTopStopAdapter extends RecyclerView.Adapter<RecyclerTopStopAdapter.RecyclerTopStopHolder> {

    @NonNull
    @Override
    public RecyclerTopStopHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_top_stop_item, viewGroup, false);
        return new RecyclerTopStopHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerTopStopHolder feedHolder, int position) {
        //用户头像
        Glide.with(feedHolder.itemView.getContext())
                .load(getAvatarResId(position))
                .centerCrop()
                .into(feedHolder.mIvAvatar);

        //内容图片
        Glide.with(feedHolder.itemView.getContext())
                .load(getContentResId(position))
                .centerCrop()
                .into(feedHolder.mIvAvatar);

        //nickname
        feedHolder.mTvNickname.setText("李白 " + position);
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

    private int getContentResId(int position) {
        switch (position % 4) {
            case 0:
                return R.drawable.taeyeon_one;
            case 1:
                return R.drawable.taeyeon_two;
            case 2:
                return R.drawable.taeyeon_three;
            case 3:
                return R.drawable.taeyeon_four;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    static class RecyclerTopStopHolder extends RecyclerView.ViewHolder {

        ImageView mIvAvatar;
        ImageView mIvContent;
        TextView mTvNickname;

        RecyclerTopStopHolder(@NonNull View itemView) {
            super(itemView);
            mIvAvatar = itemView.findViewById(R.id.iv_avatar);
            mIvContent = itemView.findViewById(R.id.iv_content);
            mTvNickname = itemView.findViewById(R.id.tv_nickname);
        }
    }

}
