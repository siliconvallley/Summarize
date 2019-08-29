package com.dh.summarize.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * @author : 86351
 * @date : 2018/12/20
 * @description : Fragment的基类
 * @version :
 */
abstract class BaseFragment : Fragment() {
    protected lateinit var mActivity: Activity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParams()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initListener()
        initData()
    }

    /**
     * 获取Activity传递过来的参数
     */
    protected open fun initParams() {

    }

    /**
     * 布局ID
     */
    abstract fun getLayoutId(): Int

    /**
     * 初始化View
     */
    abstract fun initViews(view: View)

    /**
     * 设置监听
     */
    abstract fun initListener()

    /**
     * 处理数据
     */
    abstract fun initData()

    /**
     * 设置接口方法管理类
     */
    /*fun setFunctionManager(functionsManager: FunctionsManager) {
        this.functionsManager = functionsManager
    }*/

    fun finish(activity: Activity) {
        //KeyboardUtils.hideSoftInput(activity)
        activity.finish()
    }


    fun startActivity(activity: Activity, clazz: Class<*>) {
        val intent = Intent(activity, clazz)
        startActivity(intent)
    }

    fun startActivity(activity: Activity, clazz: Class<*>, bundle: Bundle) {
        val intent = Intent(activity, clazz)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    fun startActivityForResult(activity: Activity, clazz: Class<*>, requestCode: Int) {
        val intent = Intent(activity, clazz)
        startActivityForResult(intent, requestCode)
    }

    fun startActivityForResult(activity: Activity, clazz: Class<*>, requestCode: Int, bundle: Bundle) {
        val intent = Intent(activity, clazz)
        intent.putExtras(bundle)
        startActivityForResult(intent, requestCode)
    }

    fun setResult(activity: Activity, bundle: Bundle) {
        val intent = Intent()
        intent.putExtras(bundle)
        activity.setResult(Activity.RESULT_OK, intent)
        finish(activity)
    }
}