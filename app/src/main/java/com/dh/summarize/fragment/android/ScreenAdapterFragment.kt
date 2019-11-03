package com.dh.summarize.fragment.android

import android.view.View
import com.dh.summarize.R
import com.dh.summarize.base.BaseFragment

/**
 * @author 86351
 * @date 2019/10/28
 * @description
 */
class ScreenAdapterFragment : BaseFragment() {
    companion object {
        fun getInstance() = ScreenAdapterFragment()
    }

    override fun getLayoutId(): Int {
        return R.layout.screen_adapter_fragment
    }

    override fun initViews(view: View) {
    }

    override fun initListener() {
    }

    override fun initData() {
    }
}