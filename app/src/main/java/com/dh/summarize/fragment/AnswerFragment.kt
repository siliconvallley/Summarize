package com.dh.summarize.fragment

import android.os.Bundle
import android.view.View
import com.dh.summarize.R
import com.dh.summarize.activity.answer.AnswerActivity
import com.dh.summarize.base.BaseFragment
import com.dh.summarize.global.Constants
import kotlinx.android.synthetic.main.answer_fragment_layout.*

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
        bt_list.setOnClickListener(onClickListener)
    }

    override fun initData() {
    }

    private val onClickListener = View.OnClickListener {
        val bundle = Bundle()
        when (it.id) {
            R.id.bt_list -> {
                bundle.putString(Constants.KEY_MARK, Constants.ANSWER_VALUE_LIST)
            }
        }
        startActivity(mActivity, AnswerActivity::class.java, bundle)
    }
}