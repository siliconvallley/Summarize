package com.dh.http.self

import io.reactivex.functions.Function
import okhttp3.ResponseBody
import java.io.InputStream

/**
 * @author huidingc
 * @date 2018/11/8
 * @description DownResultFunc 下载数据流转换
 * @version
 */
class DownResultFunc:Function<ResponseBody,InputStream> {
    override fun apply(t: ResponseBody): InputStream {
        return t.byteStream()
    }
}