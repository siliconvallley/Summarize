package com.dh.http.self

import com.dh.http.BaseDataF
import com.dh.http.uiils.GsonUtils
import io.reactivex.functions.Function

/**
 * @author: 86351
 * @date: 2019/4/14
 * @description: map拦截修改数据
 * @version:
 */
class HttpResultFunc1 : Function<String, BaseDataF> {
    override fun apply(t: String): BaseDataF {
        //LogUtils.d("HttpResultFunc1", "json字符串:$t")
        return GsonUtils.toObject(t)
    }
}