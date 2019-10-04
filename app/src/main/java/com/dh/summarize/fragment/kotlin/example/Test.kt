package com.dh.summarize.fragment.kotlin.example

import android.util.Log
import com.dh.summarize.fragment.kotlin.dao.TestDaoImpl
import com.dh.summarize.fragment.kotlin.entity.OperatorBean
import com.dh.summarize.fragment.kotlin.entity.UserBean
import com.dh.summarize.fragment.kotlin.entity.UserBean2
import kotlin.reflect.KClass


/**
 * @author 86351
 * @date 2019/9/28
 * @description
 */
// Kotlin是空安全的，需要我们为变量赋一个初值
var name: String = "张三"
var age: Int = 0

// Kotlin允许我们使用 类型?(String?)的形式修饰变量，告诉程序允许这个变量为空
var name1: String? = null

// Kotlin的不可变变量，类似Java的final修饰的变量，但是这并不是常量，只是一个不可赋值的变量
val age1: Int = 0


fun main() {
    // 不允许赋值，因为age1是不可变变量
    //age1 = 2

    // 因为Kotlin的空安全
    // 不允许将可以为空的变量直接赋值给不可为空的变量，必须使用!!修饰，告诉程序name1不为空
    // 如果name1为空，会直接抛出NullPointException
    name = name1!!
    // 允许将不能为空的变量直接赋值给可以为空的变量
    // 因为name1既可以接收空值，也可以接收不为空的值
    name1 = name

    Test1.log("")

    // 调用Java的class对象
    testClass(UserBean2::class.java)
    // 调用Kotlin的class
    testClass(UserBean::class)

    // kotlin中in，object是关键字，这里我们可以使用两个反引号去引用``
    Log.d("", UserBean2.`in`)
    // kotlin中在字符串中引用变量值和方法值
    // 可以使用$变量值，${对象.方法名}
    Log.d("", "输出一个值:${UserBean2.`object`}")

    `1`()

    a?.and(c.toInt())
    a?.or(c.toInt())
}


// Kotlin中创建匿名内部类的写法
object Test1 {
    fun log(str: String) {
        Log.d("", str)
    }

    @JvmStatic
    fun staticM() {

    }
}

// 调用Java的class对象
fun testClass(clazz: Class<UserBean2>) {

}

// 调用Kotlin的class，Kotlin的class是KClass
fun testClass(clazz: KClass<UserBean>) {

}

// 反引号可以使用一些不合法的方式，但是平常最好还是避免
fun `1`() {
    array.forEach {
        Log.d("", "$it==>$str")
    }

    // 输出001.length is 3
    Log.d("", "$str.length is {str.length}")

    val testDaoImpl = TestDaoImpl()
    testDaoImpl.printNumber(123)

    // 我们让编译器来推断，编译器会推断为String!，这样只会出现在Java和Kotlin互调
    val format = testDaoImpl.format("")
    // 这里会抛出NullPointException
    // String!是一个临时类型，我们只能临时去使用他，如果我们尝试调用他的方法，它就会像Java语法一样去调用，然后给我们抛出一个空指针异常
    Log.d("", "字符串长度:${format.length}")
    // 这里会抛出异常，告诉我们返回的值是空，我们的接收对象是一个不为空的类型
    // val format1: String = testDaoImpl.format("")
    val format2: String? = testDaoImpl.format("")
    // 正确，因为Kotlin是空安全的
    // 所以在Java和Kotlin互调的时候
    // 如果在接收一个Java类对象的时候，不确定接收类型是否为空，一定要将它声明成一个可空类型的
    Log.d("", "字符串长度:${format2?.length}")
}

val a: Int? = 1 // 一个装箱的 Int (java.lang.Integer)
val b: Long? = a!!.toLong() // 隐式转换产生一个装箱的 Long (java.lang.Long)

val c: Long = 1000
val d: Int = c.toInt()

val e = 1L + 3

val f: Int = 1_000_0000
val g: Long = 1_000_0000

val array: Array<String> = Array(5) { i -> (i * i).toString() }
val asc = Array(5) { i -> (i * i).toString() }

val byteArray: ByteArray = byteArrayOf()

val intArray: IntArray = intArrayOf(0, 1)
// Array of int of size 5 with values [0, 0, 0, 0, 0]
val intArray1: IntArray = IntArray(5)
// Array of int of size 5 with values [42, 42, 42, 42, 42]
val intArray2: IntArray = IntArray(5) { 20 }
// Array of int of size 5 with values [0, 1, 4, 6, 16] (values initialised to their index value)
val intArray3: IntArray = IntArray(5) { it * it }

// val str: String = 1 + "00"
val str: String = "00" + 1
val str1: String = """
    array.forEach {
        Log.d("", it)
    }
""".trimIndent()

@ExperimentalUnsignedTypes
val uInt: UInt = 20u
@ExperimentalUnsignedTypes
val uByte: UByte = 10u

