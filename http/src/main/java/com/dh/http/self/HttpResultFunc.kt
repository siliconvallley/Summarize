package com.dh.http.self

import com.dh.http.BaseData
import com.dh.http.exception.ApiException
import io.reactivex.functions.Function
import java.lang.RuntimeException

/**
 * @author huidingc
 * @date 2018/11/5
 * @description HttpResultFunc map拦截修改数据
 * @version
 */
class HttpResultFunc<T> : Function<BaseData<T>, T> {
    override fun apply(t: BaseData<T>): T? {
        if (t.code == 250){

        }else if (t.code != 200){
            val ex = ApiException(RuntimeException("unknown error"))
            ex.code = t.code
            ex.errMsg = t.message
            throw ex
        }
        return t.data
    }
}