package com.dh.summarize.activity.kotlin

import androidx.fragment.app.Fragment
import com.dh.summarize.R
import com.dh.summarize.base.BaseActivity
import com.dh.summarize.fragment.kotlin.*
import com.dh.summarize.global.Constants
import kotlinx.android.synthetic.main.activity_kotlin.*

class KotlinActivity : BaseActivity() {
    private var fragmentMark: String = Constants.KOTLIN_LAMBDA_PKG

    override fun getLayoutId(): Int {
        return R.layout.activity_kotlin
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
            Constants.KOTLIN_LAMBDA_PKG -> {
                replaceFragment(LambdaPkgFragment.getInstance())
            }
            Constants.KOTLIN_DYNAMIC_PROXY -> {
                replaceFragment(DynamicProxyFragment.getInstance())
            }
            Constants.KOTLIN_ADVANCED_FEATURES -> {
                replaceFragment(AdvanceFeatureFragment.getInstance())
            }
            Constants.KOTLIN_GRAMMAR_FEATURES -> {
                replaceFragment(GrammarFeatureFragment.getInstance())
            }
            Constants.KOTLIN_EXTEND_LIBRARY -> {
                replaceFragment(ExtendedLibraryFragment.getInstance())
            }
        }
    }

    override fun doBusiness() {

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_content, fragment, fragment::class.java.simpleName)
            .commit()
    }
}
