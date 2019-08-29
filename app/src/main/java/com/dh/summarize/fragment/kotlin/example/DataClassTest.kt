package com.dh.summarize.fragment.kotlin.example

/**
 * 数据类是Kotlin独有的类，它是被final修饰的，是不可以被继承的
 * 默认帮我们实现了copy(int age),toString(),hashCode(),equals()方法，可以通过看编译之后的JAVA文件发现
 */
data class DataClassTest (var age: Int)