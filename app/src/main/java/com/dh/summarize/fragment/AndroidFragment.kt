package com.dh.summarize.fragment

import android.view.View
import com.dh.summarize.R
import com.dh.summarize.base.BaseFragment

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
    }

    override fun initData() {
    }
}