package com.dh.static_skin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import com.dh.static_skin.R;
import com.dh.static_skin.ViewsChange;
import com.dh.static_skin.entity.AttrsBean;

/**
 * @author 86351
 * @date 2020/9/13
 * @description
 */
public class SkinImageView extends AppCompatImageView implements ViewsChange {
    private AttrsBean attrsBean;
    private Context mContext;

    public SkinImageView(@NonNull Context context) {
        this(context, null);
    }

    public SkinImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        attrsBean = new AttrsBean();
        mContext = context;

        int[] styleables = R.styleable.SkinImageView;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, styleables, defStyleAttr, 0);
        attrsBean.saveViewResource(typedArray, styleables);
        typedArray.recycle();
    }

    @Override
    public void skinChanged() {
        int key = R.styleable.SkinImageView[R.styleable.SkinImageView_android_src];
        int srcId = attrsBean.getViewResource(key);
        if (srcId > 0) {
            Drawable drawable = ContextCompat.getDrawable(mContext, srcId);
            setImageDrawable(drawable);
        }
    }
}
