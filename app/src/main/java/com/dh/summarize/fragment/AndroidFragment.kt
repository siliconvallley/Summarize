package com.dh.summarize.fragment

import android.os.Bundle
import android.view.View
import com.dh.summarize.R
import com.dh.summarize.activity.android.AndroidActivity
import com.dh.summarize.activity.android.ShapedScreenActivity
import com.dh.summarize.activity.android.TestAndroidActivity
import com.dh.summarize.base.BaseFragment
import com.dh.summarize.global.Constants
import kotlinx.android.synthetic.main.android_fragment_layout.*

class AndroidFragment : BaseFragment() {
    companion object {
        fun getInstance(): AndroidFragment {
            return AndroidFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.android_fragment_layout
    }

    override fun initViews(view: View) {
    }

    override fun initListener() {
        bt_component.setOnClickListener(onClickListener)
        btTransVal.setOnClickListener(onClickListener)
        btPaint.setOnClickListener(onClickListener)
        btCanvas.setOnClickListener(onClickListener)
        btPath.setOnClickListener(onClickListener)
        btScreenAdapter.setOnClickListener(onClickListener)
        btScreenAdapter1.setOnClickListener {
            startActivity(mActivity, ShapedScreenActivity::class.java)
        }
        btTest.setOnClickListener {
            startActivity(mActivity, TestAndroidActivity::class.java)
        }
    }

    override fun initData() {

    }

    private val onClickListener = View.OnClickListener {
        val bundle = Bundle()
        val value = when (it.id) {
            /*R.id.bt_component -> value = Constants.ANDROID_FOUR_COMPONENT
            R.id.btTransVal -> value = Constants.TRANSFER_VALUE
            R.id.btPaint -> value = Constants.TEST_PAINT
            R.id.btCanvas -> value = Constants.TEST_CANVAS*/

            R.id.bt_component -> Constants.ANDROID_FOUR_COMPONENT
            R.id.btTransVal -> Constants.TRANSFER_VALUE
            R.id.btPaint -> Constants.TEST_PAINT
            R.id.btCanvas -> Constants.TEST_CANVAS
            R.id.btPath -> Constants.TEST_PATH
            R.id.btScreenAdapter -> Constants.SCREEN_ADAPTER
            else -> ""
        }
        bundle.putString(Constants.KEY_MARK, value)
        startActivity(mActivity, AndroidActivity::class.java, bundle)
    }
}