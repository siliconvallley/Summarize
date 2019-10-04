package com.dh.summarize.fragment.kotlin.entity;

import com.dh.summarize.fragment.kotlin.example.Test2Utils;

import java.io.File;

import kotlin.io.FilesKt;
import kotlin.text.Charsets;

/**
 * @author 86351
 * @date 2019/9/28
 * @description
 */
public class UserBean2 {
    public static String in = "in";
    public static int object = 2;

    public static void main(String[] args) {
        Test2Utils.sayM(null);

        File file = new File("");
        String text = FilesKt.readText(file, Charsets.UTF_8);
    }
}
