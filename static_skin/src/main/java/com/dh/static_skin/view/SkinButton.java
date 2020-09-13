package com.dh.static_skin.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.dh.static_skin.R;
import com.dh.static_skin.ViewsChange;
import com.dh.static_skin.entity.AttrsBean;

/**
 * @author 86351
 * @date 2020/9/13
 * @description
 */
public class SkinButton extends AppCompatButton implements ViewsChange {
    private AttrsBean attrsBean;
    private Context mContext;

    public SkinButton(@NonNull Context context) {
        this(context, null);
    }

    public SkinButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        attrsBean = new AttrsBean();
        mContext = context;
        int[] styleables = R.styleable.SkinButton;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, styleables, defStyleAttr, 0);
        attrsBean.saveViewResource(typedArray, styleables);
        typedArray.recycle();
    }

    @Override
    public void skinChanged() {
        int key = R.styleable.SkinButton[R.styleable.SkinButton_android_background];
        int backgroundId = attrsBean.getViewResource(key);
        if (backgroundId > 0) {
            Drawable drawable = ContextCompat.getDrawable(mContext, backgroundId);
            setBackgroundDrawable(drawable);
        }

        key = R.styleable.SkinButton[R.styleable.SkinButton_android_textColor];
        int textColorId = attrsBean.getViewResource(key);
        if (textColorId > 0) {
            ColorStateList textColor = ContextCompat.getColorStateList(mContext, textColorId);
            setTextColor(textColor);
        }
    }
}
