package com.dh.summarize.fragment

import android.view.View
import com.dh.summarize.R
import com.dh.summarize.base.BaseFragment

class AnswerFragment : BaseFragment() {
    companion object {
        fun getInstance(): AnswerFragment {
            return AnswerFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.answer_fragment_layout
    }

    override fun initViews(view: View) {
    }

    override fun initListener() {
    }

    override fun initData() {
    }
}