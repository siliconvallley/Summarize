package com.dh.summarize.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dh.summarize.utils.DensityUtils
import java.lang.ref.WeakReference
import kotlin.reflect.KClass

/**
 * @author : 86351
 * @date : 2018/12/20
 * @description : Activity的基类
 * @version :
 */
abstract class BaseActivity : AppCompatActivity() {
    protected lateinit var mActivity: Activity
    private lateinit var activitys: WeakReference<Activity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 屏幕适配
        // DensityUtils.setCustomDensity(this.application, this)
        // 沉浸式
        //StatusBarHelper.translucent(this)
        // 修改状态栏字体颜色
        //StatusBarHelper.setLightStatusBar(this, true)
        // 设置布局
        setContentView(getLayoutId())
        // 初始化Activity
        mActivity = this
        // 获取系统保存的状态
        getSaveInstanceState(savedInstanceState)
        // 管理Activity
        activitys = WeakReference(this)
        ActivityCollector2.pushTask(activitys)
        initViews()
        initListener()
        initData()
        doBusiness()
    }

    /**
     * 获取保存的页面状态
     */
    protected open fun getSaveInstanceState(savedInstanceState: Bundle?) {

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        saveInstanceState(outState)
    }

    /**
     * 在界面销毁之前保存数据，一定会被调用
     */
    protected open fun saveInstanceState(outState: Bundle) {

    }

    /**
     * 获取布局id
     */
    abstract fun getLayoutId(): Int

    /**
     * 初始化控件，其实kotlin有插件，基本不会手写findViewById()
     * 因为插件底层帮你实现好了：没有初始化就初始化，初始化好了就从缓存获取
     */
    abstract fun initViews()

    /**
     * 设置回调监听
     */
    abstract fun initListener()

    /**
     * 初始化数据，或者处理其他的Activity传递过来的数据
     */
    abstract fun initData()

    /**
     * 所有事物的逻辑处理
     */
    abstract fun doBusiness()

    /**
     * 判断Activity是否存在
     * @return
     */
    fun isActivityExists(packageName: String, className: String): Boolean {
        val intent = Intent()
        intent.setClassName(packageName, className)
        return !(packageManager.resolveActivity(intent, 0) == null
                || intent.resolveActivity(packageManager) == null
                || packageManager.queryIntentActivities(intent, 0).size == 0)
    }

    fun startActivity(clazz: Class<*>) {
        val intent = Intent()
        intent.setClass(this, clazz)
        startActivity(intent)
    }

    fun startActivity(clazz: Class<*>, bundle: Bundle) {
        val intent = Intent()
        intent.setClass(this, clazz)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    fun startActivityForResult(clazz: Class<*>, requestCode: Int) {
        val intent = Intent()
        intent.setClass(this, clazz)
        startActivityForResult(intent, requestCode)
    }

    fun startActivityForResult(clazz: Class<*>, requestCode: Int, bundle: Bundle) {
        val intent = Intent()
        intent.setClass(this, clazz)
        intent.putExtras(bundle)
        startActivityForResult(intent, requestCode)
    }

    fun setResult(bundle: Bundle) {
        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun finish() {
        //KeyboardUtils.hideSoftInput(this)
        super.finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        activitys.clear()
        ActivityCollector2.popTask(activitys)
    }
}
