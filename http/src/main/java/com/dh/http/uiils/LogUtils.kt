package com.dh.http.uiils

import android.util.Log

/**
 * @author : Silicon_Valley
 * @date : 2018/12/18
 * @description : 打印日志的时候，存在有的厂家把低级的日志禁掉的问题
 * @version :
 */
object LogUtils {
    private val debug:Boolean = true

    fun v(tag:String,msg:String){
        if (debug) Log.v("$tag==>",msg)
    }

    fun v(tag:String,msg:String,e:Throwable){
        if (debug) Log.v("$tag==>",msg,e)
    }

    fun d(tag:String,msg:String){
        if (debug) Log.d("$tag==>",msg)
    }

    fun d(tag:String,msg:String,e:Throwable){
        if (debug) Log.d("$tag==>",msg,e)
    }

    fun i(tag:String,msg:String){
        if (debug) Log.i("$tag==>",msg)
    }

    fun i(tag:String,msg:String,e:Throwable){
        if (debug) Log.i("$tag==>",msg,e)
    }

    fun w(tag:String,msg:String){
        if (debug) Log.w("$tag==>",msg)
    }

    fun w(tag:String,msg:String,e:Throwable){
        if (debug) Log.w("$tag==>",msg,e)
    }

    fun e(tag:String,msg:String){
        if (debug) Log.e("$tag==>",msg)
    }

    fun e(tag:String,msg:String,e:Throwable){
        if (debug) Log.e("$tag==>",msg,e)
    }
}