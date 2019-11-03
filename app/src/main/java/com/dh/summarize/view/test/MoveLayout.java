package com.dh.summarize.view.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dh.http.uiils.LogUtils;

/**
 * @author 86351
 * @date 2019/11/3
 * @description
 */
public class MoveLayout extends ViewGroup {
    private static final String TAG = "MoveLayout";

    public MoveLayout(Context context) {
        super(context);
    }

    public MoveLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MoveLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            child.layout(left + child.getLeft(), top + child.getTop(), left + child.getLeft() + width, top + child.getTop() + height);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                judgeArea(event);
                break;
            case MotionEvent.ACTION_MOVE:
                moveArea(event);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    private void moveArea(MotionEvent ev) {
        float moveX = ev.getX();
        float moveY = ev.getY();
        LogUtils.INSTANCE.e(TAG, "moveX:" + moveX + "==>moveY:" + moveY);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);

            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            int halfWidth = width / 2;
            int halfHeight = height / 2;

            int calLeft = (int) (moveX - halfWidth);
            int calTop = (int) (moveY - halfHeight);

            child.setLeft(calLeft >= 0 ? calLeft : 0);
            child.setTop(calTop >= 0 ? calTop : 0);

        }

        requestLayout();
    }

    private void judgeArea(MotionEvent ev) {
        float downX = ev.getX();
        float downY = ev.getY();
        LogUtils.INSTANCE.e(TAG, "downX:" + downX + "==>downY:" + downY);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int childLeft = child.getLeft();
            int childTop = child.getTop();
            int childRight = child.getRight();
            int childBottom = child.getBottom();
            LogUtils.INSTANCE.e(TAG, "childLeft:" + childLeft + "==>childTop:" + childTop
                    + "==>childRight:" + childRight + "==>childBottom:" + childBottom);
            String touchTxt;
            if (downX >= childLeft && downX <= childRight && downY >= childTop && downY <= childBottom) {
                touchTxt = "摸到了";
            } else {
                touchTxt = "没摸到";
            }
            Toast.makeText(getContext(), touchTxt, Toast.LENGTH_SHORT).show();

            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            int halfWidth = width / 2;
            int halfHeight = height / 2;

            int calLeft = (int) (downX - halfWidth);
            int calTop = (int) (downY - halfHeight);
            int calRight = calLeft + width;
            int calBottom = calTop + height;
            LogUtils.INSTANCE.e(TAG, "calLeft:" + calLeft + "==>calTop:" + calTop
                    + "==>calRight:" + calRight + "==>calBottom:" + calBottom);


            child.setLeft(calLeft >= 0 ? calLeft : 0);
            child.setTop(calTop >= 0 ? calTop : 0);
            //child.setRight(calRight >= 0 ? calRight : 0);
            //child.setBottom(calBottom >= 0 ? calBottom : 0);
        }
        requestLayout();
    }
}
