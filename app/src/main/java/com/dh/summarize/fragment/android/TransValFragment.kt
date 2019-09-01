package com.dh.summarize.fragment.android

import android.content.Context
import android.os.Bundle
import android.view.View
import com.dh.summarize.R
import com.dh.summarize.activity.android.AndroidActivity
import com.dh.summarize.base.BaseFragment
import com.dh.utils_library.fragment.FunctionsManager
import com.dh.utils_library.utils.LogUtils
import kotlinx.android.synthetic.main.trans_val_fragment_layout.*

/**
 * @author 86351
 * @date 2019/8/31
 * @description
 */
class TransValFragment : BaseFragment(), View.OnClickListener {
    companion object {
        fun getInstance(): TransValFragment {
            val transValFragment = TransValFragment()
            val bundle = Bundle()
            bundle.putString("", "")
            transValFragment.arguments = bundle
            return transValFragment
        }

        private const val TAG = "TransValFragment"

        val INTERFACE_NO = "${TransValFragment::class.java.simpleName}NO"
        val INTERFACE_PARAM = "${TransValFragment::class.java.simpleName}PARAM"
        val INTERFACE_RESULT = "${TransValFragment::class.java.simpleName}RESULT"
        val INTERFACE_PARAM_RESULT = "${TransValFragment::class.java.simpleName}PARAM_RESULT"
    }

    //private var functionsManager: FunctionsManager? = null
    /*private lateinit var baseActivity: AndroidActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // 一般此处的接口绑定都是提取到BaseFragment中
        if (context is AndroidActivity) {
            baseActivity = context
            baseActivity.addFunctionForFragment(tag)
        }
    }*/

    override fun getLayoutId(): Int {
        return R.layout.trans_val_fragment_layout
    }

    override fun initViews(view: View) {
    }

    override fun initListener() {
        btNo.setOnClickListener(this)
        btParam.setOnClickListener(this)
        btResult.setOnClickListener(this)
        btParamResult.setOnClickListener(this)
    }

    override fun initData() {
        /*if (onFragmentListener != null) {
            onFragmentListener?.onFunction()
        }*/
    }

    /*fun setFunctionsManager(functionsManager: FunctionsManager) {
        this.functionsManager = functionsManager
    }*/

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btNo -> {
                functionsManager.invokeFunc(INTERFACE_NO)
                LogUtils.d(TAG, "fragment调用无参无返回值方法")
            }
            R.id.btParam -> {
                functionsManager.invokeFunc(INTERFACE_PARAM, "1314")
                LogUtils.d(TAG, "fragment调用有参无返回值方法")
            }
            R.id.btResult -> {
                val result = functionsManager.invokeFunc(INTERFACE_RESULT, String::class.java)
                LogUtils.d(TAG, "fragment调用无参有返回值方法，返回值:$result")
            }
            R.id.btParamResult -> {
                val result = functionsManager.invokeFunc(
                    INTERFACE_PARAM_RESULT,
                    "5201314",
                    String::class.java
                )
                LogUtils.d(TAG, "fragment调用有参有返回值方法，返回值:$result")
            }
        }
    }

    fun testM(value: Int) {
        LogUtils.d(TAG, "让Activity调用，传递值给Fragment")
    }

    private var onFragmentListener: OnFragmentListener? = null

    interface OnFragmentListener {
        fun onFunction()
    }

    fun setOnFragmentListener(onFragmentListener: OnFragmentListener) {
        this.onFragmentListener = onFragmentListener
    }
}