package com.dh.http.interceptor

import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * @author huidingc
 * @date 2018/11/1
 * @description BasicHttpParams 公共参数拦截器
 * 添加公共参数的拦截器，所有请求都需要的
 */
class BasicHttpParams : Interceptor {
    /**
     * 添加到头部的公共参数
     */
    private val headers:MutableMap<String,String> = hashMapOf()
    /**
     * 添加到URL尾部的公共参数,get和post都适用
     */
    private val urls:MutableMap<String,String> = hashMapOf()
    /**
     * 添加到body中的公共请求参数，post适用
     */
    private val bodys:MutableMap<String,String> = hashMapOf()


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        var request = chain.request()
        //添加公共参数到头部
        if (headers.isNotEmpty()){
            val builder = request.newBuilder()
            headers.entries.forEach{ map:MutableMap.MutableEntry<String,String> ->
                builder.header(map.key,map.value)
            }
            request = builder.build()
        }
        //添加公共参数到URL尾部
        if (urls.isNotEmpty()){
            val builder = request.url().newBuilder()
            urls.entries.forEach{
                builder.addQueryParameter(it.key,it.value)
            }
            request = request.newBuilder().url(builder.build()).build()
        }
        //添加公共参数到body中
        if (request.method() == "post" && request.body() is FormBody){
            val newBuilder = FormBody.Builder()
            if (bodys.isNotEmpty()){
                bodys.entries.forEach{
                    newBuilder.addEncoded(it.key,it.value)
                }
            }
            val formBody:FormBody = request.body() as FormBody
            for (i in 0 until formBody.size()){
                newBuilder.addEncoded(formBody.encodedName(i),formBody.encodedValue(i))
            }
            request = request.newBuilder().post(newBuilder.build()).build()
        }
        return chain.proceed(request)
    }

    class Builder {
        private var interceptor: BasicHttpParams = BasicHttpParams()

        /**
         * 添加公共参数到头
         */
        fun addHeaderParam(key:String,value:String): Builder {
            interceptor.headers[key] = value
            return this
        }

        fun addHeaderParam(map: MutableMap<String,String>): Builder {
            interceptor.headers.putAll(map)
            return this
        }

        fun addUrlParam(key:String,value:String): Builder {
            interceptor.urls[key] = value
            return this
        }

        fun addUrlParam(map: MutableMap<String,String>): Builder {
            interceptor.urls.putAll(map)
            return this
        }

        fun addBodyParam(key:String,value:String): Builder {
            interceptor.bodys[key] = value
            return this
        }

        fun addBodyParam(map: MutableMap<String,String>): Builder {
            interceptor.bodys.putAll(map)
            return this
        }

        fun build(): BasicHttpParams {
            return interceptor
        }
    }
}
