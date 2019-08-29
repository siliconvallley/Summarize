package com.dh.summarize.fragment.kotlin.entity

/**
 * operator：将一个函数标记为重载一个操作符或者实现一个约定
 */
class OperatorBean(var name: String, var age: Int) {
    // 这里是固定写法component1(),必须是component+一个数字
    operator fun component1() = name
    operator fun component2() = age
}