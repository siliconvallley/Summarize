package com.dh.utils_library.fragment

/**
 * @author : 86351
 * @date : 2019/8/31
 * @description : FunctionNoParamNoResult 有参数没有返回值
 * @version :
 */
abstract class FunctionWithParamOnly<Param>(functionName: String) : Function(functionName) {

    abstract fun function(param: Param)
}