package com.dh.static_skin;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.LayoutInflaterCompat;

import com.dh.static_skin.utils.ActionBarUtils;
import com.dh.static_skin.utils.NavigationUtils;
import com.dh.static_skin.utils.StatusBarUtils;

/**
 * @author 86351
 * @date 2020/9/13
 * @description
 */
public class SkinActivity extends AppCompatActivity {
    private SkinAppCompatViewInflater viewInflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory2(LayoutInflater.from(this), this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        if (openSkin()) {
            if (null == viewInflater) {
                viewInflater = new SkinAppCompatViewInflater(context);
            }
            viewInflater.setName(name);
            viewInflater.setAttrs(attrs);
            return viewInflater.createView();
        }
        return super.onCreateView(parent, name, context, attrs);
    }

    protected boolean openSkin() {
        return false;
    }

    public void changeDayOrNight() {
        int uiMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        switch (uiMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                setDayNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                setDayNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
        }
    }

    /**
     * 设置白天 / 黑夜模式
     *
     * @param mode
     */
    public void setDayNightMode(int mode) {
        getDelegate().setLocalNightMode(mode);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StatusBarUtils.forStatusBar(this); // 改变状态栏 颜色
            ActionBarUtils.forActionBar(this); // 标题栏 颜色
            NavigationUtils.forNavigation(this); // 虚拟按钮栏 颜色
        }
        View decorView = getWindow().getDecorView();
        changeSkinAction(decorView);
    }

    private void changeSkinAction(View decorView) {
        // 实现了ViewsChange接口，才有资格换肤
        if (decorView instanceof ViewsChange) {
            ViewsChange viewsChange = (ViewsChange) decorView;
            viewsChange.skinChanged();
        }

        if (decorView instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) decorView;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childAt = viewGroup.getChildAt(i);
                changeSkinAction(childAt);
            }
        }
    }
}
