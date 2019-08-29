package com.dh.summarize.fragment.kotlin

import android.view.View
import com.dh.summarize.R
import com.dh.summarize.base.BaseFragment
import com.dh.summarize.fragment.kotlin.example.DataClassTest
import com.dh.utils_library.utils.LogUtils
import kotlinx.android.synthetic.main.dynamic_proxy_fragment_layout.*

/**
 * Kotlin中的动态代理是通过by关键字实现
 * Kotlin的动态代理效率是要比JAVA高的
 *         Kotlin的动态代理在编译的以后会转变为静态代理
 *         Java的动态代理是通过反射区调用的
 */
class DynamicProxyFragment : BaseFragment(), View.OnClickListener {

    companion object {
        fun getInstance(): DynamicProxyFragment {
            return DynamicProxyFragment()
        }

        private const val TAG = "DynamicProxyFragment"
    }

    override fun getLayoutId(): Int {
        return R.layout.dynamic_proxy_fragment_layout
    }

    override fun initViews(view: View) {
    }

    override fun initListener() {
        bt_dynamic.setOnClickListener(this)
    }

    override fun initData() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_dynamic -> {
                // 调用了Dog的eat方法
                // 如果Pig类实现了eat方法，就会调用Pig的eat方法
                Pig(Dog()).eat()
                val dataClass = DataClassTest(10)
                dataClass.copy(age = 20)
            }
        }
    }

    private interface Animal {
        fun eat()
    }

    private class Dog : Animal {
        override fun eat() {
            LogUtils.d(TAG, "狗吃肉")
        }
    }

    private class Pig(animal: Animal) : Animal by animal


    /**
     * 枚举类
     */
    enum class EnumMode {
        A, B, C
    }

    /**
     * 密闭类，kotlin中更强大的enum，Kotlin中一般使用密闭类，很少使用枚举
     * 因为密闭类有更强大的扩展性
     */
    sealed class SealedMode {
        object A : SealedMode()
        object B : SealedMode()
        object C : SealedMode()

        class E(var age: Int) : SealedMode()
    }

    fun exec(age: Int, sealMode: SealedMode) {
        when (sealMode) {
            SealedMode.A -> {

            }
            SealedMode.B -> {

            }
            SealedMode.C -> {

            }
            is SealedMode.E -> {
                //val eMode = sealMode
                //eMode.age
                sealMode.age
            }
        }
    }
}