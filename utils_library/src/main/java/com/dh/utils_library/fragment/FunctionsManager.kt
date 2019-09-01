package com.dh.utils_library.fragment

import android.text.TextUtils
import com.dh.utils_library.utils.LogUtils

/**
 * @author 86351
 * @date 2019/8/31
 * @description
 */
class FunctionsManager private constructor() {
    // 需要在构造方法中初始化的对象在init{}中进行
    private var mFunctionNoParamNoResult: MutableMap<String, FunctionNoParamNoResult> =
        mutableMapOf()
    private var mFunctionWithParamOnly: MutableMap<String, FunctionWithParamOnly<*>> =
        mutableMapOf()
    private var mFunctionWithResultOnly: MutableMap<String, FunctionWithResultOnly<*>> =
        mutableMapOf()
    private var mFunctionWithParamWithResult: MutableMap<String, FunctionWithParamWithResult<*, *>> =
        mutableMapOf()

    private object FunctionManagerHolder {
        val instance = FunctionsManager()
    }

    companion object {
        fun getInstance(): FunctionsManager {
            return FunctionManagerHolder.instance
        }

        private const val TAG: String = "FunctionsManager"
    }

    /**
     * 添加无参无返回值的方法
     */
    fun addFunction(function: FunctionNoParamNoResult): FunctionsManager {
        mFunctionNoParamNoResult[function.functionName] = function
        return this
    }

    fun invokeFunc(funcName: String) {
        if (TextUtils.isEmpty(funcName)) {
            return
        }
        val noParamNoResult = mFunctionNoParamNoResult[funcName]
        if (noParamNoResult != null) {
            noParamNoResult.function()
        } else {
            LogUtils.d(TAG, "The method was not found==>$funcName")
            throw FunctionException("The method was not found:$funcName")
        }
    }

    /**
     * 添加有参无返回值的方法
     */
    fun addFunction(function: FunctionWithParamOnly<*>): FunctionsManager {
        mFunctionWithParamOnly[function.functionName] = function
        return this
    }

    @Suppress("UNCHECKED_CAST")
    fun <Param> invokeFunc(funcName: String, data: Param) {
        if (TextUtils.isEmpty(funcName)) {
            return
        }
        val withParamOnly: FunctionWithParamOnly<Param>? =
            mFunctionWithParamOnly[funcName] as FunctionWithParamOnly<Param>?
        if (withParamOnly != null) {
            withParamOnly.function(data)
        } else {
            try {
                throw FunctionException("The method was not found:$funcName")
            } catch (e: FunctionException) {
                LogUtils.d(TAG, "The method was not found", e)
            }
        }
    }

    /**
     * 添加无参有返回值的方法
     */
    fun addFunction(function: FunctionWithResultOnly<*>): FunctionsManager {
        mFunctionWithResultOnly[function.functionName] = function
        return this
    }

    @Suppress("UNCHECKED_CAST")
    fun <Result> invokeFunc(funcName: String, clazz: Class<Result>?): Result? {
        if (TextUtils.isEmpty(funcName)) {
            return null
        }
        val withResultOnly = mFunctionWithResultOnly[funcName]
        if (withResultOnly != null) {
            return if (clazz != null) {
                clazz.cast(withResultOnly.function())
            } else {
                withResultOnly.function() as Result
            }
        } else {
            try {
                throw FunctionException("The method was not found:$funcName")
            } catch (e: FunctionException) {
                LogUtils.d(TAG, "The method was not found", e)
            }
        }
        return null
    }

    /**
     * 添加有参有返回值的方法
     */
    fun addFunction(function: FunctionWithParamWithResult<*, *>): FunctionsManager {
        mFunctionWithParamWithResult[function.functionName] = function
        return this
    }

    @Suppress("UNCHECKED_CAST")
    fun <Param, Result> invokeFunc(funcName: String, data: Param, clazz: Class<Result>?): Result? {
        if (TextUtils.isEmpty(funcName)) {
            return null
        }
        val withParamWithResult: FunctionWithParamWithResult<Param, Result>? =
            mFunctionWithParamWithResult[funcName] as FunctionWithParamWithResult<Param, Result>?
        if (withParamWithResult != null) {
            return if (clazz != null) {
                clazz.cast(withParamWithResult.function(data))
            } else {
                withParamWithResult.function(data)
            }
        } else {
            try {
                throw FunctionException("The method was not found:$funcName")
            } catch (e: FunctionException) {
                LogUtils.d(TAG, "The method was not found", e)
            }
        }
        return null
    }

    fun removeAll() {
        if (mFunctionNoParamNoResult.isNotEmpty()) {
            mFunctionNoParamNoResult.clear()
        }
        if (mFunctionWithParamOnly.isNotEmpty()) {
            mFunctionWithParamOnly.clear()
        }
        if (mFunctionWithResultOnly.isNotEmpty()) {
            mFunctionWithResultOnly.clear()
        }
        if (mFunctionWithParamWithResult.isNotEmpty()) {
            mFunctionWithParamWithResult.clear()
        }
    }
}