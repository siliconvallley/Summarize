package com.dh.summarize.fragment.kotlin.example

import com.dh.summarize.fragment.kotlin.dao.Animal
import com.dh.summarize.fragment.kotlin.dao.Dog
import java.io.File

/**
 * @author 86351
 * @date 2019/9/28
 * @description
 */

fun main() {
    val age = 20
    val name = "张三"

    //println("我叫%d，我今年%d岁", name, age)
    println("我叫$name，我今年${age}岁")

    val animal = Animal()
    //animal.name()
    animal.printName(animal)

    val dog = Dog()
    //dog.name()
    //dog.printName(dog)
    dog.printName(dog)

}

object Test2Utils {
    @JvmStatic
    fun sayM(msg: String?) {
        println("$msg")
    }
}

fun test1() {

}

fun test1(data: String?): String? {
    return data ?: "NULL"
}

fun add(a: Int = 20, b: Int = 10): Int = a + b

/**
 * 用途：在某些条件下触发递归的函数，或者不希望被外部函数访问到的函数
 */
fun test2() {
    val data = "2019"


    fun test3(number: Int = 10) {
        println("访问外部函数的局部变量:$data")

        if (number > 0) {
            println(number - 1)
        }
    }

    test3()
}

fun test4() {
    val file: File = File("")
    file.readBytes()
    file.readText()
}

fun Animal.name() = "Animal"
fun Dog.name() = "Dog"

fun Animal.printName(animal: Animal) {
    println(animal.name())
}

fun Dog.printName(dog: Dog) {
    println(dog.name())
}

val thread = Thread {

}

