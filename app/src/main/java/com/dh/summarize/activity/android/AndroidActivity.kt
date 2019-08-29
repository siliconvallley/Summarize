package com.dh.summarize.activity.android

import com.dh.summarize.R
import com.dh.summarize.base.BaseActivity
import com.dh.summarize.global.Constants
import kotlinx.android.synthetic.main.activity_android.*

class AndroidActivity : BaseActivity() {
    private var fragmentMark: String = Constants.ANDROID_FOUR_COMPONENT

    override fun getLayoutId(): Int {
        return R.layout.activity_android
    }

    override fun initViews() {
        setSupportActionBar(tool_bar)
    }

    override fun initListener() {
        tool_bar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun initData() {
        val bundle = intent.extras
        if (bundle != null) {
            fragmentMark = bundle.getString(Constants.KEY_MARK, fragmentMark)
        }
        when (fragmentMark) {
            Constants.ANDROID_FOUR_COMPONENT -> {

            }
        }
    }

    override fun doBusiness() {
    }
}
