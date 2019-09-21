package com.dh.summarize.fragment.kotlin

import android.view.View
import com.dh.summarize.R
import com.dh.summarize.base.BaseFragment
import com.dh.utils_library.utils.LogUtils
import kotlinx.android.synthetic.main.extended_library_fragment_layout.*
import kotlinx.coroutines.*

/**
 * 1、协程
 *    通过提升CPU利用率，减少线程切换，进而提升程序的运行效率
 *    1、可控制：协程能做到可被控制的发起子任务
 *    2、轻量级：协程非常小，占用资源比线程还少
 *    3、语法糖：使多任务或多线程切换不再使用回调语法
 * 2、特殊的启动协程的方式
 *    1、
 *
 * suspend 在kotlin中被suspend关键字的修饰的函数，是一个协程函数，
 *         被suspend修饰的函数只能被suspend修饰的函数（或lambda）调用。
 *         被suspend修饰的函数（或lambda），被编译后会多出一个参数类型Continuation
 *         协程的异步调用本质上是一次回调
 */
class ExtendedLibraryFragment : BaseFragment(), View.OnClickListener {

    companion object {
        fun getInstance() = ExtendedLibraryFragment()

        private const val TAG = "ExtendedLibraryFragment"
    }

    override fun getLayoutId(): Int {
        return R.layout.extended_library_fragment_layout
    }

    override fun initViews(view: View) {
    }

    override fun initListener() {
        bt_coroutines.setOnClickListener(this)
        bt_anr.setOnClickListener(this)
    }

    override fun initData() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_coroutines -> {
                testCoroutines()
            }
            R.id.bt_anr -> {
            }
        }
    }

    /**
     * 启动协程三种方式
     * 1、runBlocking 从线程环境切换到协程环境
     * 2、launch
     * 3、async/await一般是成对存在的，返回的是Deferred对象，Deferred是继承与Job的接口，
     *    其中添加了await函数用来获取async启动协程之后的返回结果
     */
    private fun testCoroutines() = runBlocking {
        val job = launch {
            repeat(10) {
                LogUtils.d(TAG, "执行$it...")
                // 将协程挂起500ms
                delay(500)
            }
        }

        val job2: Deferred<String> = async {
            delay(500)
            return@async "返回一个协程"
        }
        LogUtils.d(TAG, "协程结果:${job2.await()}...")

        delay(1300)
        LogUtils.d(TAG, "线程等待中...")
        // 取消协程
        job.cancel()
        // 其实这句代码写在取消之后是不会生效的
        //job.join()
        LogUtils.d(TAG, "线程结束...")
    }

    fun test(){

    }

    /**
     * context: CoroutineContext 协程与协程之间切换传递参数的功能
     */
    /*public fun CoroutineScope.launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        val newContext = newCoroutineContext(context)
        val coroutine = if (start.isLazy)
            LazyStandaloneCoroutine(newContext, block) else
            StandaloneCoroutine(newContext, active = true)
        coroutine.start(start, coroutine, block)
        return coroutine
    }*/
}