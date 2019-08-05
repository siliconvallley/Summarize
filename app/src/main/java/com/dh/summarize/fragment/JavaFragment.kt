package com.dh.summarize.fragment

import android.view.View
import com.dh.summarize.R
import com.dh.summarize.base.BaseFragment

class JavaFragment : BaseFragment() {
    companion object {
        fun getInstance(): JavaFragment {
            return JavaFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.java_fragment_layout
    }

    override fun initViews(view: View) {
    }

    override fun initListener() {
    }

    override fun initData() {
    }
}