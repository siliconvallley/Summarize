package com.dh.summarize.fragment.android;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.CallSuper;
import androidx.annotation.CheckResult;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorLong;
import androidx.annotation.ColorRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntDef;
import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import androidx.annotation.Size;
import androidx.annotation.StringRes;
import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;

import com.dh.summarize.R;
import com.dh.summarize.base.BaseFragment;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author 86351
 * @date 2019/10/10
 * @description
 */
public class AnnotationFragment extends BaseFragment {
    public static final int RED = 0;
    public static final int GREEN = 1;
    public static final int BLUE = 2;
    private TextView tvContent;

    @IntDef({RED, GREEN, BLUE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LightColors {
    }

    private StringBuilder builder = new StringBuilder();

    public static AnnotationFragment getInstance() {
        return new AnnotationFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.annotation_fragment_layout;
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
        setColor(AnnotationFragment.BLUE);
        builder.append(toString("Test @NotNull @Nullable")).append("\n");
        setProgress(50);
        setName("123456");
        setArray(new int[]{1, 2});
        setMultiData("2x");
        setRes(R.string.close);
        setTextColor(Color.parseColor("#ff75de"));
        builder.append(getName()).append("\n");
        updateViews();
        callSuper();


        tvContent.setText(builder.toString());
    }

    public void setColor(@LightColors int color) {
        builder.append(color).append("\n");
    }

    @Nullable
    public String toString(@NotNull String fromStr) {
        return fromStr;
    }

    /**
     * 区间范围
     *
     * @param progress
     * @IntRange
     * @FloatRange
     */
    public void setProgress(@FloatRange(from = 0.0f, to = 100.0f) float progress) {
        builder.append(progress).append("\n");
    }

    /**
     * 限制长度
     *
     * @param name
     */
    public void setName(@Size(6) String name) {
        builder.append(name).append("\n");
    }

    /**
     * 限制最大长度
     *
     * @param array
     */
    public void setArray(@Size(max = 2) int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                builder.append(array[i]).append("\n");
            } else {
                builder.append(array[i]).append(",");
            }
        }
    }

    /**
     * 设置允许的长度是2的倍数
     *
     * @param data
     */
    public void setMultiData(@Size(multiple = 2) String data) {
        builder.append(data).append("\n");
    }

    /**
     * 资源注解
     * AnimRes
     * AnimatorRes
     * AnyRes
     * ArrayRes
     * AttrRes
     * BoolRes
     * ColorRes
     * DimenRes
     * DrawableRes
     * FractionRes
     * IdRes
     * IntegerRes
     * InterpolatorRes
     * LayoutRes
     * MenuRes
     * PluralsRes
     * RawRes
     * StringRes
     * StyleRes
     * StyleableRes
     * TransitionRes
     * XmlRes
     *
     * @param res
     */
    public void setRes(@StringRes int res) {
        builder.append("资源注解::").append(getString(res)).append("\n");
    }

    /**
     * @param color
     * @ColorRes 颜色资源
     * @ColorInt 输入颜色int值
     * @ColorLong
     */
    public void setTextColor(@ColorInt int color) {
        // tvContent.setTextColor(color);
        builder.append("传递int类型颜色值::").append(color).append("\n");
    }

    /**
     * 返回结果的注解，用来注解方法，如果一个方法得到了结果，却没有使用这个结果，
     * 就会有错误出现，一旦出现这种错误，就说明你没有正确使用该方法。
     * @return
     */
    @CheckResult
    public String getName() {
        return "检查返回结果是否使用";
    }

    /**
     * 线程相关
     * @UiThread 通常可以等同于主线程,标注方法需要在UIThread执行,比如 View类就使用这个注解
     * @MainThread 主线程, 经常启动后创建的第一个线程
     * @WorkerThread 工作者线程, 一般为一些后台的线程, 比如AsyncTask里面的doInBackground就是这样的.
     * @BinderThread 注解方法必须要在BinderThread线程中执行, 一般使用较少.
     */
    @UiThread
    public void updateViews(){
        builder.append("UI线程").append("\n");
    }

    /**
     * 重写的方法必须要调用super方法
     * Activity的onCreate，onResume等
     *
     */
    @CallSuper
    public void callSuper(){
        builder.append("CallSuper").append("\n");
    }
}
