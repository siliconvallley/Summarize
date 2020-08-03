package com.dh.summarize.aspectj;


import android.content.Context;

import androidx.fragment.app.Fragment;

import com.dh.summarize.R;
import com.dh.summarize.annotation.ClickBehavior;
import com.dh.utils_library.utils.LogUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Objects;

/**
 * @author 86351
 * @date 2020/8/3
 * @description
 */
@Aspect
public class ClickBehaviorAspect {
    private static final String TAG = "Aspect_ClickBehavior";

    // 1、应用中用到了哪些注解，放到当前的切入点进行处理（找到需要处理的切入点）
    // execution，以方法执行时作为切点，触发Aspect类
    // * *(..) 可以处理ClickBehavior这个类所有的方法
    @Pointcut("execution(@com.dh.summarize.annotation.ClickBehavior * *(..))")
    public void methodPointCut() {
    }

    // 2、对切入点进行处理
    // 固定写法  1)返回值Object  2)参数ProceedingJoinPoint joinPoint
    // 3)抛出异常 Throwable
    @Around("methodPointCut()")
    public Object dealPointCut(ProceedingJoinPoint joinPoint) throws Throwable {
        Context context;
        if (joinPoint.getThis() instanceof Fragment) {
            Fragment fragment = (Fragment) joinPoint.getThis();
            context = fragment.getActivity();
        } else {
            context = (Context) joinPoint.getThis();
        }
        // 获取签名方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 获取方法所属的类名
        String clazzName = methodSignature.getDeclaringType().getSimpleName();
        // 获取方法名
        String methodName = methodSignature.getName();
        // 获取方法的注解值(需要统计的用户行为)
        //String annotationValue = methodSignature.getMethod().getAnnotation(ClickBehavior.class).value();
        String annotationValue = Objects.requireNonNull(methodSignature.getMethod().getAnnotation(ClickBehavior.class)).value();
        // 统计方法的执行时间、统计用户点击某功能行为。（存储到本地，每过x天上传到服务器）
        LogUtils.d(TAG, "方法开始>>>>");
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;
        LogUtils.d(TAG, "方法结束>>>>>");
        LogUtils.d(TAG, String.format(context.getResources().getString(R.string.output_user_behavior),
                annotationValue, clazzName, methodName, duration));
        return result;
    }
}
