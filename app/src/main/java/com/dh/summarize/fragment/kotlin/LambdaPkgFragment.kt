package com.dh.summarize.fragment.kotlin

import android.view.View
import com.dh.summarize.R
import com.dh.summarize.base.BaseFragment
import com.dh.utils_library.utils.LogUtils
import kotlinx.android.synthetic.main.lambda_pkg_fragment_layout.*

/**
 * Kotlin的lambda闭包
 * kotlin 1.3之前是有参数限制的（如果参数超过限制会抛出一个方法未定义的异常，kotlin/Function22），上限是22，具体可以参考Functions.kt文件
 *    解决方式: 参考本项目kotlin包下面的JAVA类，为什么不用kotlin的方式，因为kotlin中只有系统标准库才允许
 *             使用kotlin的包名，所以根据JAVA和kotlin可以互相调用，所以声明成JAVA类
 * kotlin 1.3之后，如果参数上限超过了22个，会走FunctionN，查阅编译后的java类
 * lambda22 = (Function22)null.INSTANCE;
 * lambda23 = (FunctionN)null.INSTANCE;
 * this.lambda22 = (Function22)null.INSTANCE;
 * this.lambda23 = (FunctionN)null.INSTANCE;
 * print = (Function1)null.INSTANCE;
 * 具体可以参考FunctionN.kt类，通过源码可以看到这个方法是在1.3以及之后再新增的
 * （就是官方解决了这个问题，我们在使用的时候就不用去考虑解决的问题）
 */
class LambdaPkgFragment : BaseFragment(), View.OnClickListener {
    companion object {
        private const val TAG = "LambdaPkgFragment"
        fun getInstance(): LambdaPkgFragment {
            return LambdaPkgFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.lambda_pkg_fragment_layout
    }

    override fun initViews(view: View) {
    }

    override fun initListener() {
        bt_param.setOnClickListener(this)
        bt_param_deal.setOnClickListener(this)
    }

    override fun initData() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_param -> {
                lambda22(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
                //lambda23(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)

            }
            R.id.bt_param_deal -> {

            }
        }
    }

    private val lambda22 =
        { param1: Int, param2: Int, param3: Int, param4: Int, param5: Int, param6: Int, param7: Int, param8: Int, param9: Int, param10: Int,
          param11: Int, param12: Int, param13: Int, param14: Int, param15: Int, param16: Int, param17: Int, param18: Int, param19: Int, param20: Int,
          param21: Int, param22: Int ->
            LogUtils.d(TAG, "我刚好22个参数")
        }

    private val lambda23 =
        { param1: Int, param2: Int, param3: Int, param4: Int, param5: Int, param6: Int, param7: Int, param8: Int, param9: Int, param10: Int,
          param11: Int, param12: Int, param13: Int, param14: Int, param15: Int, param16: Int, param17: Int, param18: Int, param19: Int, param20: Int,
          param21: Int, param22: Int, param23: Int ->
            LogUtils.d(TAG, "我刚好23个参数")
        }
}