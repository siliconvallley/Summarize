package com.dh.http.exception

/**
 * @author huidingc
 * @date 2018/11/5
 * @description ApiException 异常类
 * @version
 */
class ApiException:Exception {
    var code:Int = 0
    var errMsg:String? = null
    var ex:Throwable? = null

    constructor(ex:Throwable):super(ex)

    constructor(msg:String,code: Int,ex:Throwable):super(msg,ex){
        this.errMsg = msg
        this.ex = ex
        this.code = code
    }
}