package com.dh.summarize.fragment

import android.view.View
import com.dh.summarize.R
import com.dh.summarize.base.BaseFragment

class AlgorithmFragment : BaseFragment() {
    companion object {
        fun getInstance(): AlgorithmFragment {
            return AlgorithmFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.algorithm_fragment_layout
    }

    override fun initViews(view: View) {
    }

    override fun initListener() {
    }

    override fun initData() {
    }
}