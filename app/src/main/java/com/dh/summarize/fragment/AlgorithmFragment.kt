package com.dh.summarize.fragment

import android.os.Bundle
import android.view.View
import com.dh.summarize.R
import com.dh.summarize.activity.algorithm.AlgorithmActivity
import com.dh.summarize.base.BaseFragment
import com.dh.summarize.global.Constants
import kotlinx.android.synthetic.main.algorithm_fragment_layout.*

class AlgorithmFragment : BaseFragment(), View.OnClickListener {

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
        bt_array.setOnClickListener(this)
    }

    override fun initData() {
    }

    override fun onClick(v: View?) {
        val bundle = Bundle()
        when (v?.id) {
            R.id.bt_array -> {
                bundle.putString(Constants.KEY_MARK, Constants.AL_ARRAY)
            }
        }
        startActivity(mActivity, AlgorithmActivity::class.java, bundle)
    }
}