package com.dh.utils_library.fragment

/**
 * @author : 86351
 * @date : 2019/8/31
 * @description : FunctionNoParamNoResult 有返回值没有参数
 * @version :
 */
abstract class FunctionWithResultOnly<Result>(functionName: String) : Function(functionName) {

    abstract fun function(): Result
}