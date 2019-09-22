package com.dh.http.interceptor

import android.content.Context
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author huidingc
 * @date 2018/11/8
 * @description CacheInterceptor 缓存拦截器
 * @version
 */
class CacheInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (!NetworkUtils.isConnected(context)) {
            request = request.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE)
                .build()
        }
        val response = chain.proceed(request)
        if (NetworkUtils.isConnected(context)) {
            //有网络是这是缓存为0
            val maxAge = 0
            response.newBuilder()
                .header("Cache-Control", "public, max-age=$maxAge")
                // 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                .removeHeader("Pragma")
                .build()
        } else {
            // 无网络设置缓存时间为4周
            val maxStale = 60 * 60 * 24 * 28
            response.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .removeHeader("Pragma")
                .build()
        }
        return response
    }
}