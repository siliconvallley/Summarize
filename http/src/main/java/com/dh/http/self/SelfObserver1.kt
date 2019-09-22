package com.dh.http.self

import com.dh.http.BaseDataF
import com.dh.http.NetCallBack
import com.dh.http.exception.ApiException
import com.dh.http.exception.ExceptionCode
import com.dh.http.exception.ExceptionFactory
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @author: 86351
 * @date: 2019/3/10
 * @description: 统一处理异常
 * @version:
 */
class SelfObserver1 constructor(private var callBack: NetCallBack<BaseDataF>) : Observer<BaseDataF> {
    private val TAG: String = "SelfObserver1"

    override fun onComplete() {
        //LogUtils.d(TAG, "data load complete!")
    }

    override fun onSubscribe(d: Disposable) {
        callBack.onSubscribe(d)
    }

    override fun onNext(t: BaseDataF) {
        when (t.code) {
            200 -> {
                callBack.onSuccess(t)
            }
            ExceptionCode.NO_PASSWORD_CODE -> {
                val ex = ApiException(RuntimeException(t.message))
                ex.code = t.code
                ex.errMsg = t.message
                callBack.onError(ex)
            }
            ExceptionCode.NORMAL_ERROR_CODE -> {
                val ex = ApiException(RuntimeException(t.message))
                ex.code = t.code
                ex.errMsg = t.message
                callBack.onError(ex)
            }
            ExceptionCode.ERROR_CODE_400,
            ExceptionCode.ERROR_CODE_401,
            ExceptionCode.ERROR_CODE_402,
            ExceptionCode.ERROR_CODE_403,
            ExceptionCode.ERROR_CODE_405,
            ExceptionCode.ERROR_CODE_410,
            ExceptionCode.ERROR_CODE_411,
            ExceptionCode.ERROR_CODE_414 -> {
                val ex = ApiException(RuntimeException(t.message))
                ex.code = t.code
                ex.errMsg = t.message
                callBack.onError(ex)
            }
            else -> {
                val ex = ApiException(RuntimeException(t.message))
                ex.code = t.code
                ex.errMsg = t.message
                callBack.onError(ex)
            }
        }
    }

    override fun onError(e: Throwable) {
        /*if (!NetworkUtils.isConnected()) {
            val ex = ApiException(e)
            ex.code = ExceptionCode.NETWORK_ERROR
            ex.errMsg = ExceptionCode.NETWORK_ERROR_MSG
            callBack.onError(ex)
        } else {
            val ex = ExceptionFactory.transformException(e)
            callBack.onError(ex)
        }*/
        val ex = ExceptionFactory.transformException(e)
        callBack.onError(ex)
    }
}