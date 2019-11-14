package com.dh.summarize.activity.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.dh.summarize.R;
import com.google.android.material.appbar.MaterialToolbar;

/**
 * @author 86351
 * @date 2019/11/14
 * @description ImmersiveActivity
 * 测试沉浸式
 * CardView
 */
public class ImmersiveActivity extends AppCompatActivity {
    private MaterialToolbar toolBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immersive);

        toolBar = findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);


        immersive();
        setTopPadding(toolBar, this);
    }

    private void immersive() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            int visibility = window.getDecorView().getSystemUiVisibility();
            // 设置内容全屏显示
            visibility |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            // 隐藏虚拟导航栏
            //visibility |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            // 防止内容区域大小发生变化
            visibility |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            window.getDecorView().setSystemUiVisibility(visibility);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

    }

    public int getStatusBarHeight(Context context) {
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            return context.getResources().getDimensionPixelSize(resId);
        }
        return 0;
    }

    public void setTopPadding(View view, Context context) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        //layoutParams.height += getStatusBarHeight(context);
        view.setPadding(view.getPaddingStart(), view.getPaddingTop() + getStatusBarHeight(context),
                view.getPaddingEnd(), view.getPaddingBottom());
    }
}
