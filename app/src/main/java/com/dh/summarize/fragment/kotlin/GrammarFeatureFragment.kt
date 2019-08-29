package com.dh.summarize.fragment.kotlin

import android.view.View
import com.dh.summarize.R
import com.dh.summarize.base.BaseFragment
import com.dh.summarize.fragment.kotlin.entity.UserBean
import com.dh.summarize.fragment.kotlin.entity.UserBean1
import com.dh.utils_library.utils.LogUtils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.grammar_feature_fragment_layout.*

/**
 * 1、变量、常量与只读
 * 2、空安全是如何实现的
 *    基本每种语言都是通过这两种方式去解决的
 *   Ⅰ、每次引用对象的时候，都去进行对象判空，在运行期避免对象空指针（这也是我们平时常用的做法）
 *   Ⅱ、通过静态代码检查，编译插件检查，在编译器避免空指针异常
 *    Kotlin的空实现是这两种方式的结合
 *   Ⅰ、在编译期间通过可以为空类型和不可以为空类型将对象的两种形态彻底的分离，在编译期就不能对不可为空的对象赋值一个空值
 *       可以为空：var name: String? = null
 *       不可为空：var sex: String = ""，所以在赋初始值的时候，不能给对象赋值null，kotlin在声明期间就会直接报错
 *       可以为空和不可为空区别就是在对象类型后跟?
 *    Ⅱ、编译期：kotlin有全局推断的功能，如果在之前的代码中对对象进行了判空，那么在接下来的代码中对象都是处于一个不为空的状态。
 *        但是全局变量有可能会在其他方法为对象赋空值，局部变量一般是可以通过上下文推断来避免对一个对象进行多次判空
 * 3、内联的特殊情况
 * 4、Kotlin的真泛型与实现方法
 */
//private const val A = 0 //top-level变量
class GrammarFeatureFragment : BaseFragment(), View.OnClickListener {

    companion object {
        fun getInstance() = GrammarFeatureFragment()

        /**
         * 常量
         * const只能修饰object属性，或者top-level变量（因为kotlin中，变量是可以写在类外面的）
         * const变量的值必须在编译期间确定下来，所以它的类型只能是String或者基本类型
         */
        private const val TAG = "GrammarFeatureFragment"
    }

    private var name: String? = null
        get() {
            return "var get:$field"
        }
        set(value) {
            field = "var set:$value"
        }
    private val age: Int = 20
        get() {
            return field + 2
        }

    override fun getLayoutId(): Int {
        return R.layout.grammar_feature_fragment_layout
    }

    override fun initViews(view: View) {
    }

    override fun initListener() {
        bt_variable.setOnClickListener(this)
        bt_inline.setOnClickListener(this)
        bt_generic.setOnClickListener(this)
    }

    override fun initData() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_variable -> {
                variableM()
            }
            R.id.bt_inline -> {
                testInline()
            }
            R.id.bt_generic -> {
                testGeneric()
            }
        }
    }

    private fun testGeneric() {
        val testGeneric = TestGeneric<TestGeneric1>()
        testGeneric.add(TestGeneric1())
        //Gson().fromJson<UserBean>("")
        val a = Views<Presenter>().presenter
        val b = Views.Companion.invoke<Presenter>().presenter
        LogUtils.d(TAG, a.toString())
        LogUtils.d(TAG, b.toString())
    }

    private fun variableM() {
        // var和val声明的变量本质区别是val声明的变量是不能重写setter方法的
        // val声明的变量不等于常量（可以看成final修饰的变量），只是不允许我们对它再次赋值，我们可以通过重写它的getter方法来达到修改变量值的效果
        val builder = StringBuilder()
        builder.append("$name\n")
        name = "张三"
        builder.append("$name\n")
        builder.append("为val修饰的只读变量返回结果加2:$age\n")

        val userBean1 = UserBean1(1995)
        builder.append("val修饰年龄:${userBean1.age}\n")
        userBean1.oneYearLater()
        builder.append("修改val修饰年龄:${userBean1.age}\n")
        tv_content.text = builder.toString()
    }

    private fun testInline() {
        inlineM {
            LogUtils.d(TAG, "inline1")
            // 1、中断后面的执行，这样直接返回return，testInline()方法后的代码都不会执行
            // 2、这就是我们通过lambda去中断了当前外部函数的执行，是因为我们对inlineM添加了inline修饰符才可以
            // 3、如果inlineM不用inline修饰，kotlin是不允许我们直接添加return关键字的，
            // 只允许我们去返回当前的这个闭包return@inlineM，不会中断外部函数的执行
            // 4、如果我们不想当前的lambda去中断外部函数的执行，就用crossinline关键字去修饰我们传入的lambda表达式
            return@inlineM
        }
        LogUtils.d(TAG, "inline2")

        // 匿名函数
        // return也只会中断return，不会去中断外部函数执行
        /*val test = fun() {
            LogUtils.d(TAG, "inline3")
            return
        }*/
        // Kotlin中是允许使用内部函数的（函数中嵌套函数）
        // 其实也可以将这个匿名函数看成是一个内部函数
        fun test() {
            LogUtils.d(TAG, "inline3")
            return
        }
        test()

        inlineM2({
            LogUtils.d(TAG, "inline4")
        }, testOutput::output)

        inlineM2(testOutput::output) {
            LogUtils.d(TAG, "inline5")
        }
    }

    /**
     * 内联函数
     * inline修饰的方法在执行的时候，lambda是会被展开的，变成语句调用
     * crossinline关键字修饰了lambda是不会被内联
     */
    private inline fun inlineM(crossinline l: () -> Unit) {
        l.invoke()
        return
    }

    /**
     * noinline通常用来修饰，返回值是Lambda
     */
    private inline fun inlineM2(l: () -> Unit, noinline l1: () -> Unit): () -> Unit {
        l.invoke()
        l1.invoke()
        return l1
    }

    private interface TestOutput {
        fun output()
    }

    private val testOutput = object : TestOutput {
        override fun output() {
            LogUtils.d(TAG, "test inline")
        }
    }

    private interface CallBack {
        fun callBack()
    }

    /**
     * Kotlin的泛型
     * 可以通过where关键字限制T同时满足是CallBack，并且是Runnable
     */
    private class TestGeneric<T> where T : CallBack, T : Runnable {
        fun add(t: T) {
            t.callBack()
            t.run()
        }
    }

    private class TestGeneric1 : CallBack, Runnable {
        override fun callBack() {
            LogUtils.d(TAG, "测试泛型CallBack")
        }

        override fun run() {
            LogUtils.d(TAG, "测试泛型Runnable")
        }
    }

    /**
     * Kotlin真泛型
     * reified关键字，但是这个关键字只能修饰函数，不能修饰类
     * 必须加上inline关键字
     */
    inline fun <reified T> Gson.fromJson(json: String): T {
        return fromJson(json, T::class.java)
    }


    class Presenter {
        override fun toString(): String {
            return "Presenter"
        }
    }

    /**
     * 可以使用下面的方式去改造kotlin中的MVP调用
     * 在Activity或者Fragment中不用显示去返回Presenter具体的类
     */
    class Views<T>(clazz: Class<T>) {
        val presenter: T by lazy { clazz.newInstance() }

        companion object {
            inline operator fun <reified T> invoke() = Views(T::class.java)
        }
    }
}