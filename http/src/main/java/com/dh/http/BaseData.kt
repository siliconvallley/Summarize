package com.dh.http

/**
 * @author : 86351
 * @date : 2018/12/20
 * @description : 接口数据的基类
 * @version :
 */
class BaseData<T> {
    var code: Int = 0
    var message: String? = null
    var index: Int = 0
    var count: Int = 0
    //var data: String? = null
    var data: T? = null
}
