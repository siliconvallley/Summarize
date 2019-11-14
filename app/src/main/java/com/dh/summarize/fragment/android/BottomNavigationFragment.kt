package com.dh.summarize.fragment.android

import android.view.View
import android.widget.Toast
import com.dh.summarize.R
import com.dh.summarize.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.bottom_navigation_fragment.*

/**
 * @author 86351
 * @date 2019/10/28
 * @description  使用BottomNavigationView
 */
class BottomNavigationFragment : BaseFragment() {
    companion object {
        fun getInstance() = BottomNavigationFragment()
    }

    override fun getLayoutId(): Int {
        return R.layout.bottom_navigation_fragment
    }

    override fun initViews(view: View) {

    }

    override fun initListener() {
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> tvMsg.text = getString(R.string.title_home)
                R.id.menu_dash -> tvMsg.text = getString(R.string.title_dashboard)
                R.id.menu_notification -> tvMsg.text = getString(R.string.title_notifications)

            }
            return@setOnNavigationItemSelectedListener true
        }
        fab.setOnClickListener {
            Snackbar.make(it, "SnackBar测试", 2000)
                .setAction("Toast") {
                    Toast.makeText(mActivity,"Toast",Toast.LENGTH_SHORT).show()
                }
                .show()
        }
    }

    override fun initData() {

    }
}