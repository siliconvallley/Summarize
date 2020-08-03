package com.dh.summarize.activity

import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.dh.summarize.R
import com.dh.summarize.base.ActivityCollector2
import com.dh.summarize.base.BaseActivity
import com.dh.summarize.fragment.*
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author 86351
 * @date 2019/11/6
 * @description DrawerLayout，ToolBar，NavigationView的结合使用
 */
class MainActivity : BaseActivity() {
    companion object {
        private const val INTERVAL_TIME: Long = 2000
    }

    private val androidFragment by lazy { AndroidFragment.getInstance() }
    private val javaFragment by lazy { JavaFragment.getInstance() }
    private val kotlinFragment by lazy { KotlinFragment.getInstance() }
    private val algorithmFragment by lazy { AlgorithmFragment.getInstance() }
    private val answerFragment by lazy { AnswerFragment.getInstance() }
    private lateinit var fragments: MutableList<Fragment>
    /**
     * 记录每次点击的tab下标
     */
    private var preIndex: Int = -1
    private var firstTime: Long = 0

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initViews() {
        tool_bar.title = getString(R.string.menu_android)
        setSupportActionBar(tool_bar)
        val actionBar = actionBar
        if (actionBar != null) {
            // 设置ToolBar home按钮可点击
            actionBar.setHomeButtonEnabled(true)
            // 设置显示ToolBar home键图标
            actionBar.setDisplayHomeAsUpEnabled(true)
            // 设置ToolBar  home键图标
            //actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher)
        }
        val drawerToggle =
            object : ActionBarDrawerToggle(
                this,
                drawer_layout,
                tool_bar,
                R.string.open,
                R.string.close
            ) {
                override fun onDrawerStateChanged(newState: Int) {
                    super.onDrawerStateChanged(newState)
                }

                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                    super.onDrawerSlide(drawerView, slideOffset)
                }

                override fun onDrawerOpened(drawerView: View) {
                    super.onDrawerOpened(drawerView)
                }

                override fun onDrawerClosed(drawerView: View) {
                    super.onDrawerClosed(drawerView)
                }
            }
        drawerToggle.syncState()
        drawer_layout.addDrawerListener(drawerToggle)
    }

    override fun initListener() {
        // NavigationView的监听
        nav_view.setNavigationItemSelectedListener(navItemSelectListener)
        //drawer_layout.addDrawerListener(drawerListener)
    }

    override fun initData() {
        //val intentService = IntentService
        /*val sp: SharedPreferences = getSharedPreferences("", Context.MODE_PRIVATE)
        sp.edit {
            putBoolean("", true)
        }
        sp.edit().putBoolean("", true).apply()*/
        fragments = arrayListOf()
        fragments.add(androidFragment)
        fragments.add(javaFragment)
        fragments.add(kotlinFragment)
        fragments.add(algorithmFragment)
        fragments.add(answerFragment)
    }

    override fun doBusiness() {
        selectFragment(0)
        //tool_bar.title = getString(R.string.menu_android)
    }

    /**
     * NavigationView的监听
     */
    private val navItemSelectListener =
        NavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_android -> {
                    selectFragment(0)
                }
                R.id.menu_java -> {
                    selectFragment(1)
                }
                R.id.menu_kotlin -> {
                    selectFragment(2)
                }
                R.id.menu_algorithm -> {
                    selectFragment(3)
                }
                R.id.menu_answer -> {
                    selectFragment(4)
                }
            }
            tool_bar.title = item.title.toString()
            // 打开侧滑菜单
            //drawer_layout.openDrawer(GravityCompat.START)
            // 关闭侧滑菜单
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }

    /**
     * DrawerLayout监听
     */
    private val drawerListener = object : DrawerLayout.DrawerListener {
        override fun onDrawerStateChanged(newState: Int) {
        }

        override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
        }

        override fun onDrawerClosed(drawerView: View) {

        }

        override fun onDrawerOpened(drawerView: View) {
        }

    }

    /**
     * Fragment的切换
     */
    private fun selectFragment(index: Int) {
        // 判断是否是相同下标，避免重复点击
        if (preIndex == index) return
        // 开启事物
        val transaction = supportFragmentManager.beginTransaction()
        if (preIndex == -1) {
            transaction.add(
                R.id.fl_content,
                fragments[index],
                fragments[index].javaClass.simpleName
            )
        } else {
            // 隐藏前一个Fragment
            transaction.hide(fragments[preIndex])
            // 判断当前Fragment是否被添加过
            if (!fragments[index].isAdded) {
                transaction.add(
                    R.id.fl_content,
                    fragments[index],
                    fragments[index].javaClass.simpleName
                )
            } else {
                transaction.show(fragments[index])
            }
        }
        transaction.commit()
        // 重新赋值
        preIndex = index
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        drawer_layout.removeDrawerListener(drawerListener)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_DOWN) {
            val secondTime: Long = System.currentTimeMillis()
            if (secondTime - firstTime > INTERVAL_TIME) {
                Toast.makeText(mActivity, getString(R.string.down_exit), Toast.LENGTH_SHORT).show()
                firstTime = secondTime
                return true
            } else {
                ActivityCollector2.popTaskAll()
                //杀死应用的进程
                android.os.Process.killProcess(android.os.Process.myPid())
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    /**
     * 创建ToolBar的菜单
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.test_toolbar_menu, menu)
        return true
    }

    /**
     * 菜单的事件监听
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_android -> {

            }
            R.id.menu_java -> {}
        }
        return super.onOptionsItemSelected(item)
    }
}
