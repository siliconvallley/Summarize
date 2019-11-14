package com.dh.summarize.activity.android

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.dh.summarize.R
import com.dh.summarize.base.BaseActivity
import com.dh.summarize.base.BaseFragment
import com.dh.summarize.fragment.android.*
import com.dh.summarize.global.Constants
import com.dh.summarize.utils.DensityUtils
import com.dh.utils_library.fragment.*
import com.dh.utils_library.utils.LogUtils
import kotlinx.android.synthetic.main.activity_android.*

private const val TAG = "AndroidActivity"

class AndroidActivity : BaseActivity() {
    private var fragmentMark: String = Constants.ANDROID_FOUR_COMPONENT
    private lateinit var functionsManager: FunctionsManager

    override fun getLayoutId(): Int {
        DensityUtils.setCustomDensity(this.application, this)
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
            Constants.TRANSFER_VALUE -> {
                replaceFragment(TransValFragment.getInstance())
            }
            Constants.TEST_PAINT -> replaceFragment(PaintFragment.getInstance())
            Constants.TEST_CANVAS -> replaceFragment(CanvasFragment.getInstance())
            Constants.TEST_PATH -> replaceFragment(PathFragment.getInstance())
            Constants.SCREEN_ADAPTER -> replaceFragment(ScreenAdapterFragment.getInstance())
            Constants.BOTTOM_NAVIGATION -> replaceFragment(BottomNavigationFragment.getInstance())
            Constants.MATERIAL_DESIGN -> replaceFragment(MaterialDesignFragment.getInstance())
            Constants.RECYCLER_TOP_STOP -> replaceFragment(RecyclerTopStopFragment.getInstance())
        }
    }

    override fun doBusiness() {

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_content, fragment, fragment::class.java.simpleName)
            .commit()
    }

    fun addFunctionForFragment(tag: String?) {
        val fragment: BaseFragment = supportFragmentManager.findFragmentByTag(tag) as BaseFragment
        functionsManager = FunctionsManager.getInstance()
        fragment.setFunctionManager(
            functionsManager.addFunction(object :
                FunctionNoParamNoResult(TransValFragment.INTERFACE_NO) {
                override fun function() {
                    LogUtils.d(TAG, "无参无返回值的方法被调用")
                }
            }).addFunction(object :
                FunctionWithParamOnly<String>(TransValFragment.INTERFACE_PARAM) {
                override fun function(param: String) {
                    LogUtils.d(TAG, "有参无返回值的方法被调用，传递过来的参数:$param")
                }
            }).addFunction(object :
                FunctionWithResultOnly<String>(TransValFragment.INTERFACE_RESULT) {
                override fun function(): String {
                    LogUtils.d(TAG, "无参有返回值的方法被调用")
                    return "我是有参无返回值的方法"
                }
            }).addFunction(object :
                FunctionWithParamWithResult<String, String>(TransValFragment.INTERFACE_PARAM_RESULT) {
                override fun function(param: String): String {
                    LogUtils.d(TAG, "有参有返回值的方法被调用，传递过来的参数:$param")
                    return "我是有参有返回值的方法"
                }
            })
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        functionsManager.removeAll()
    }

    fun getToolBar(): Toolbar {
        return tool_bar
    }
}
