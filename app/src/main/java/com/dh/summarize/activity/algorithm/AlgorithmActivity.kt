package com.dh.summarize.activity.algorithm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.dh.summarize.R
import com.dh.summarize.base.BaseActivity
import com.dh.summarize.fragment.algorithm.ArrayFragment
import com.dh.summarize.fragment.algorithm.TestAlgorithmFragment
import com.dh.summarize.global.Constants
import kotlinx.android.synthetic.main.activity_algorithm.*

class AlgorithmActivity : BaseActivity() {
    private var fragmentMark: String = Constants.AL_ARRAY

    override fun getLayoutId(): Int {
        return R.layout.activity_algorithm
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
    }

    override fun doBusiness() {
        when (fragmentMark) {
            Constants.AL_ARRAY -> {
                replaceFragment(ArrayFragment.getInstance())
            }
            Constants.AL_TEST_1 -> {
                replaceFragment(TestAlgorithmFragment.getInstance())
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_content, fragment, fragment::class.java.simpleName)
            .commit()
    }
}
