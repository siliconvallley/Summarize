package com.dh.summarize.activity.answer

import androidx.fragment.app.Fragment
import com.dh.summarize.R
import com.dh.summarize.base.BaseActivity
import com.dh.summarize.fragment.answer.IteratorListFragment
import com.dh.summarize.global.Constants
import kotlinx.android.synthetic.main.activity_answer.*

class AnswerActivity : BaseActivity() {
    private var fragmentMark: String = Constants.ANSWER_VALUE_LIST

    override fun getLayoutId(): Int {
        return R.layout.activity_answer
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
            Constants.ANSWER_VALUE_LIST -> {
                /*FragmentUtils.replaceFragment(
                    supportFragmentManager,
                    IteratorListFragment.getInstance(),
                    R.id.fl_content
                )*/
                replaceFragment(IteratorListFragment.getInstance())
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
