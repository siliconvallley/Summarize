package com.dh.summarize.fragment.algorithm

import android.view.View
import com.dh.summarize.R
import com.dh.summarize.base.BaseFragment
import com.dh.summarize.fragment.algorithm.data_struct.TestOne
import com.dh.utils_library.utils.LogUtils
import kotlinx.android.synthetic.main.test_algorithm_fragment_layout.*

/**
 * @author 86351
 * @date 2019/9/11
 * @description
 */
class TestAlgorithmFragment : BaseFragment(), View.OnClickListener {

    companion object {
        fun getInstance() = TestAlgorithmFragment()
        private const val TAG = "TestAlgorithmFragment"
    }

    override fun getLayoutId(): Int {
        return R.layout.test_algorithm_fragment_layout
    }

    override fun initViews(view: View) {
    }

    override fun initListener() {
        btSum.setOnClickListener(this)
    }

    override fun initData() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btSum -> {
                val twoSum = TestOne.getInstance().twoSum1(intArrayOf(2, 7, 11, 15), 9)
                for (index in twoSum) {
                    LogUtils.d(TAG, "数组:$index")
                }
            }
        }
    }

    /**
     * 1、[2, 7, 11, 15] target=9
     *
     */
}