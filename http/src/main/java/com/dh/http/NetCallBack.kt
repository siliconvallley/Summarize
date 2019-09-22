package com.dh.http


import com.dh.http.exception.ApiException
import io.reactivex.disposables.Disposable

/**
 * @autor huidingc
 * @date 2018/2/1
 * @description NetCallBack
 * @version 网络访问接口处理类
 */
interface NetCallBack<T> {
    /**
     * 访问开始
     * @param disposable
     */
    fun onSubscribe(disposable: Disposable)

    /**
     * 访问成功
     * @param t
     */
    fun onSuccess(t: T)

    /**
     * 网络访问出错
     * @param exception
     */
    fun onError(exception: ApiException)
}
