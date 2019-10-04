package com.dh.summarize.fragment.kotlin.example

/**
 * @author 86351
 * @date 2019/9/29
 * @description
 */

fun main() {
    /*onlyIf(true) {
        println("我是Lambda的高阶函数")
    }*/

    val runnable = Runnable {
        println("runnable::run")
    }
    val function: () -> Unit
    function = runnable::run

    onlyIf(true, function)
}

/**
 * 函数的第二个参数是一个Lambda表达式
 * block参数的类型是：一个参数为空，返回值是Unit的函数
 */
inline fun onlyIf(isDebug: Boolean, block: () -> Unit) {
    if (isDebug) {
        block()
    }
}