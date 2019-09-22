package com.dh.http

import android.app.Application
import android.content.Context
import com.dh.http.interceptor.BasicHttpParams
import com.dh.http.interceptor.CacheInterceptor
import com.dh.http.interceptor.HttpLoggingInterceptor
import com.dh.http.progress.ProgressListener
import com.dh.http.progress.ProgressResponseBody
import com.dh.http.uiils.LogUtils
import com.google.gson.Gson
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @author huidingc
 * @date 2018/10/31
 * @description RetrofitManager
 * @version
 */
class RetrofitManager private constructor() {
    private val TAG: String = "RetrofitManager"
    /**
     * 上下文application
     */
    private lateinit var application: Application
    /**
     * 连接超时
     */
    private val DEFAULT_CONNECT_TIMEOUT: Long = 20
    /**
     * 读取超时
     */
    private val DEFAULT_READ_TIMEOUT: Long = 20
    /**
     * 写入超时
     */
    private val DEFAULT_WRITE_TIMEOUT: Long = 20
    /**
     * 最大缓存容量
     */
    private val CACHE_MAXSIZE: Long = 1024 * 1024 * 50L
    /**
     * get方法
     */
    private val METHOD_GET = "GET"
    /**
     * post方法
     */
    private val METHOD_POST = "POST"
    /**
     * Retrofit对象
     */
    private var retrofit: Retrofit? = null
    /**
     * 缓存文件
     */
    private val CACHE_NAME: String = "test_meet"
    /**
     * 下载进度监听
     */
    private lateinit var progressListener: ProgressListener

    private object InstanceHolder {
        val INSTANCE: RetrofitManager = RetrofitManager()
    }

    companion object {
        val instance: RetrofitManager by lazy { InstanceHolder.INSTANCE }

        /*private var instance:RetrofitManager? = null
        fun getInstance():RetrofitManager{
            if (instance == null){
                synchronized(RetrofitManager::class){
                    if (instance == null){
                        instance = RetrofitManager()
                    }
                }
            }
            return instance!!
        }*/

        /*private var instance:RetrofitManager? = null
        val INSTANCE:RetrofitManager
        get() {
            if (instance == null){
                synchronized(RetrofitManager::class){
                    if (instance == null){
                        instance = RetrofitManager()
                    }
                }
            }
            return instance!!
        }*/
    }

    /**
     * 创建URL接口对象
     * @param clazz
     */
    fun <T> createService(clazz: Class<T>): T {
        return retrofit(isDown = false).create(clazz)
    }

    /**
     * 创建下载的URL接口对象
     * @param clazz
     * @param progressListener 下载进度监听
     */
    fun <T> createService(clazz: Class<T>, progressListener: ProgressListener): T {
        this.progressListener = progressListener
        return retrofit(isDown = true).create(clazz)
    }

    /**
     * 初始化
     */
    fun init(application: Application) {
        this.application = application
    }

    /**
     * 获取上下文
     */
    fun getContext(): Context {
        return application
    }

    /**
     * 实例化Retrofit
     * @return
     */
    private fun retrofit(isDown: Boolean): Retrofit {
        if (null == retrofit) {
            val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            //设置连接超时
            builder.connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            //设置读取超时
            builder.readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)
            //设置写入超时
            builder.writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS)
            //错误重连
            builder.retryOnConnectionFailure(true)

            if (isDown) {
                //添加下载拦截器
                addDwonInterceptor(builder)
            } else {
                //设置缓存
                addCacheInterceptor(builder)
                //添加公共参数
                addPublicInterceptor(builder)
            }

            //设置日志拦截器
            addLogInterceptor(builder)
            //addLogInterceptor1(builder)

            retrofit = Retrofit.Builder()
                .baseUrl(BaseHost.HOST)
                .client(builder.build())
                //添加ScalarsConverterFactory，可以获取服务返回的json字符串
                .addConverterFactory(ScalarsConverterFactory.create())
                //添加GsonConverterFactory，直接将json字符串转换为我们需要的对象
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                //使用RxJava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return retrofit!!
    }

    /**
     * 添加缓存
     */
    private fun addCacheInterceptor(builder: OkHttpClient.Builder) {
        val cacheFile = File(application.externalCacheDir, CACHE_NAME)
        val cache = Cache(cacheFile, CACHE_MAXSIZE)
        builder.addInterceptor(CacheInterceptor(application)).cache(cache)
    }

    /**
     * 添加日志拦截器
     */
    private fun addLogInterceptor(builder: OkHttpClient.Builder) {
        val logInterceptor = HttpLoggingInterceptor {
            LogUtils.d(TAG, it)
        }
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(logInterceptor)
    }

    /**
     * 自定义日志拦截器
     */
    private fun addLogInterceptor1(builder: OkHttpClient.Builder) {
        val logInterceptor = Interceptor {
            val request = it.request()
            val response = it.proceed(request)
            val body = response.body()
            val mediaType = body!!.contentType()
            val content = body.string()
            LogUtils.e(TAG, "contentType:$mediaType")
            LogUtils.e(TAG, "content:$content")
            LogUtils.e(TAG, "content-length:${body.contentLength()}")
            return@Interceptor response.newBuilder()
                .body(ResponseBody.create(mediaType, content))
                .build()
        }
        builder.addInterceptor(logInterceptor)
    }

    /**
     * 添加公共参数拦截器
     */
    private fun addPublicInterceptor(builder: OkHttpClient.Builder) {
        val publicInterceptor = BasicHttpParams.Builder()
            .addHeaderParam("Content-Type", "application/json;charset=UTF-8")
            .addHeaderParam("Accept", "application/json;charset=UTF-8")
            //.addHeaderParam("Authorization", SpUserUtils.getToken())
            //.addHeaderParam("AuthorizationType", "client")
            .build()
        builder.addInterceptor(publicInterceptor)
    }

    /**
     * 下载拦截器
     */
    private fun addDwonInterceptor(builder: OkHttpClient.Builder) {
        val downInterceptor = Interceptor {
            val response = it.proceed(it.request())
            return@Interceptor response.newBuilder()
                .body(ProgressResponseBody(response.body()!!, progressListener))
                .build()
        }
        builder.addInterceptor(downInterceptor)
    }
}