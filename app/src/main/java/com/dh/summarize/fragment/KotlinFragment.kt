package com.dh.summarize.fragment

import android.os.Bundle
import android.view.View
import com.dh.summarize.R
import com.dh.summarize.activity.kotlin.KotlinActivity
import com.dh.summarize.base.BaseFragment
import com.dh.summarize.global.Constants
import kotlinx.android.synthetic.main.kotlin_fragment_layout.*

class KotlinFragment : BaseFragment() {
    companion object {
        fun getInstance(): KotlinFragment {
            return KotlinFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.kotlin_fragment_layout
    }

    override fun initViews(view: View) {
    }

    override fun initListener() {
        bt_lambda.setOnClickListener(onClickListener)
        bt_dynamic.setOnClickListener(onClickListener)
        bt_high.setOnClickListener(onClickListener)
        bt_grammar.setOnClickListener(onClickListener)
        bt_extend.setOnClickListener(onClickListener)
    }

    override fun initData() {
    }

    private val onClickListener = View.OnClickListener {
        val bundle = Bundle()
        when (it.id) {
            R.id.bt_lambda -> {
                bundle.putString(Constants.KEY_MARK, Constants.KOTLIN_LAMBDA_PKG)
            }
            R.id.bt_dynamic -> {
                bundle.putString(Constants.KEY_MARK, Constants.KOTLIN_DYNAMIC_PROXY)
            }
            R.id.bt_high -> {
                bundle.putString(Constants.KEY_MARK, Constants.KOTLIN_ADVANCED_FEATURES)
            }
            R.id.bt_grammar -> {
                bundle.putString(Constants.KEY_MARK, Constants.KOTLIN_GRAMMAR_FEATURES)
            }
            R.id.bt_extend -> {
                bundle.putString(Constants.KEY_MARK, Constants.KOTLIN_EXTEND_LIBRARY)
            }
        }
        startActivity(mActivity, KotlinActivity::class.java, bundle)
    }
}