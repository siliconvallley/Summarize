package com.dh.summarize.fragment.kotlin.example

import java.io.File

val lambda22 =
    { param1: Int, param2: Int, param3: Int, param4: Int, param5: Int, param6: Int, param7: Int, param8: Int, param9: Int, param10: Int,
      param11: Int, param12: Int, param13: Int, param14: Int, param15: Int, param16: Int, param17: Int, param18: Int, param19: Int, param20: Int,
      param21: Int, param22: Int ->
        println("我刚好22个参数")
    }

val lambda23 =
    { param1: Int, param2: Int, param3: Int, param4: Int, param5: Int, param6: Int, param7: Int, param8: Int, param9: Int, param10: Int,
      param11: Int, param12: Int, param13: Int, param14: Int, param15: Int, param16: Int, param17: Int, param18: Int, param19: Int, param20: Int,
      param21: Int, param22: Int, param23: Int ->
        println("我刚好23个参数")
    }

typealias A = File
typealias CustomList<T> = MutableList<T>

val print = { name: String ->
    println(name)
}

val map = mapOf<String, String>("key" to "键", "value" to "值")

fun main(args: Array<String>) {
    //lambda22(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
    print("测试下lambda")
    lambda23(
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1
    )
    for ((key, value) in map) {
        println("键:$key==>值:$value")
    }
}