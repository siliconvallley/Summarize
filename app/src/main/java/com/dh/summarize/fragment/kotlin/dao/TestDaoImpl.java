package com.dh.summarize.fragment.kotlin.dao;

import android.util.Log;

import com.dh.summarize.fragment.kotlin.example.Test1;
import com.dh.summarize.fragment.kotlin.example.TestKt;

/**
 * @author 86351
 * @date 2019/9/28
 * @description
 */
public class TestDaoImpl implements TestDao {
    private static final String TAG = "TestDaoImpl";

    @Override
    public void printNumber(int number) {
        Log.d(TAG, "输出int类型的数字：" + number);
    }

    @Override
    public void printNumber(Integer number) {
        Log.d(TAG, "输出Integer封装类型的数字：" + number);
    }

    public String format(String data) {
        return data.isEmpty() ? null : data;
    }

    public void printCustom() {
        Test1.INSTANCE.log("");
        Test1.staticM();
    }
}
