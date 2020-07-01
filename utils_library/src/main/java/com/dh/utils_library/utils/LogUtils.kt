package com.dh.utils_library.utils

import android.util.Log

/**
 * @author : Silicon_Valley
 * @date : 2018/12/18
 * @description : 打印日志的时候，存在有的厂家把低级的日志禁掉的问题
 * @version :
 */
object LogUtils {
    //private val debug:Boolean = Utils.isDebug()
    private val debug: Boolean = true

    @JvmStatic
    fun v(tag: String, msg: String) {
        if (debug) Log.v("$tag==>", msg)
    }

    @JvmStatic
    fun v(tag: String, msg: String, e: Throwable) {
        if (debug) Log.v("$tag==>", msg, e)
    }

    @JvmStatic
    fun d(tag: String, msg: String) {
        if (debug) Log.d("$tag==>", msg)
    }

    @JvmStatic
    fun d(tag: String, msg: String, e: Throwable) {
        if (debug) Log.d("$tag==>", msg, e)
    }

    @JvmStatic
    fun i(tag: String, msg: String) {
        if (debug) Log.i("$tag==>", msg)
    }

    @JvmStatic
    fun i(tag: String, msg: String, e: Throwable) {
        if (debug) Log.i("$tag==>", msg, e)
    }

    @JvmStatic
    fun w(tag: String, msg: String) {
        if (debug) Log.w("$tag==>", msg)
    }

    @JvmStatic
    fun w(tag: String, msg: String, e: Throwable) {
        if (debug) Log.w("$tag==>", msg, e)
    }

    @JvmStatic
    fun e(tag: String, msg: String) {
        if (debug) Log.e("$tag==>", msg)
    }

    @JvmStatic
    fun e(tag: String, msg: String, e: Throwable) {
        if (debug) Log.e("$tag==>", msg, e)
    }
}