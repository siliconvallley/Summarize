package com.dh.http

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

/**
 * @author huidingc
 * @date 2018/11/5
 * @description HttpApiService 接口文件
 * @version
 */
interface HttpApiService {

    /*@FormUrlEncoded
    @POST("user/login")
    fun login(@Field("username") userName: String, @Field("password") password: String): Observable<BaseData<Login>>

    @FormUrlEncoded
    @POST("user/login")
    fun login(@FieldMap map:MutableMap<String,String>): Observable<BaseData<MutableList<Login>>>

    @POST("user/login")
    fun login(@Body login: Login): Observable<BaseData<Login>>*/

    //@HTTP(method = "GET",path = "",hasBody = false)
    //fun hh(@Body usbRequest: UsbRequest)
    /**
     * 上传单个文件
     */
    @Multipart
    @POST("file/batch/upload")
    fun upload(@Header("X-Token") token:String,@Part file:MultipartBody.Part):Observable<BaseData<MutableList<String>>>

    /**
     * 批量上传文件
     */
    @Multipart
    @POST("file/batch/upload")
    fun upload(@Header("X-Token") token:String,requestBody: RequestBody):Observable<BaseData<MutableList<String>>>

    /**
     * 文件下载
     */
    @Streaming
    @GET
    fun download(@Url url: String): Observable<ResponseBody>

    @Streaming
    @GET("{name}")
    fun download1(@Path("name") name:String): Observable<ResponseBody>

}