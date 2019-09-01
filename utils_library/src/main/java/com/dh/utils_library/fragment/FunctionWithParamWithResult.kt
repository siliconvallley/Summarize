package com.dh.utils_library.fragment

/**
 * @author : 86351
 * @date : 2019/8/31
 * @description : FunctionNoParamNoResult 既有返回值又有结果
 * @version :
 */
abstract class FunctionWithParamWithResult<Param, Result>(functionName: String) :
    Function(functionName) {

    abstract fun function(param: Param): Result
}