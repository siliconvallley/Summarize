package com.dh.http.exception

import android.net.ParseException
import com.dh.http.exception.ApiException
import com.dh.http.exception.ExceptionCode
import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * @author huidingc
 * @date 2018/11/6
 * @description ExceptionFactory
 * @version
 */
object ExceptionFactory {

    fun transformException(ex:Throwable): ApiException {
        val apiEx = ApiException(ex)
        if (ex is HttpException){
            apiEx.code = ex.code()
            apiEx.errMsg = ExceptionCode.HTTP_ERROR_MSG
            apiEx.ex = ex
        }else if (ex is ConnectException ||ex is SocketException || ex is SocketTimeoutException){
            apiEx.code = ExceptionCode.HTTP_ERROR
            apiEx.errMsg = ExceptionCode.CONNECT_ERROR_MSG
            apiEx.ex = ex
        }else if (ex is JSONException || ex is JsonParseException || ex is ParseException){
            apiEx.code = ExceptionCode.JSON_ERROR
            apiEx.errMsg = ExceptionCode.JSON_ERROR_MSG
            apiEx.ex = ex
        }else if (ex is UnknownHostException){
            apiEx.code = ExceptionCode.UNKNOWNHOST_ERROR
            apiEx.errMsg = ExceptionCode.UNKNOWNHOST_ERROR_MSG
            apiEx.ex = ex
        }else {
            apiEx.code = ExceptionCode.UNKNOWN_ERROR
            // apiEx.errMsg = ExceptionCode.UNKNOWN_ERROR_MSG
            apiEx.errMsg = ex.message
            apiEx.ex = ex
        }
        return apiEx
    }
}