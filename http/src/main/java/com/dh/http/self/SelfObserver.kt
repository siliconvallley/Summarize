package com.dh.http.self

import com.dh.http.BaseData
import com.dh.http.NetCallBack
import com.dh.http.exception.ApiException
import com.dh.http.exception.ExceptionCode
import com.dh.http.exception.ExceptionFactory
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.lang.RuntimeException

/**
 * @author huidingc
 * @date 2018/11/6
 * @description SelfObserver1 统一处理异常
 * @version
 */
class SelfObserver<T> constructor(private var callBack: NetCallBack<T>) : Observer<BaseData<T>> {
    companion object {
        private const val TAG: String = "SelfObserver"
    }

    override fun onComplete() {
        //LogUtils.d(TAG, "data load complete!")
    }

    override fun onSubscribe(d: Disposable) {
        callBack.onSubscribe(d)
    }

    override fun onNext(t: BaseData<T>) {
        when (t.code) {
            // 成功
            200 -> {
                callBack.onSuccess(t.data!!)
            }
            // 支付密码不存在
            ExceptionCode.NO_PASSWORD_CODE -> {
                val ex = ApiException(RuntimeException(t.message))
                ex.code = t.code
                ex.errMsg = t.message
                callBack.onError(ex)
                //LogUtils.e(TAG, "error:${t.message.toString()}==>code:${t.code}")
            }
            // 服务器返回错误
            ExceptionCode.NORMAL_ERROR_CODE -> {
                val ex = ApiException(RuntimeException(t.message))
                ex.code = t.code
                ex.errMsg = t.message
                callBack.onError(ex)
                //LogUtils.e(TAG, "error:${t.message.toString()}==>code:${t.code}")
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
        //LogUtils.e(TAG, "error:${e.message.toString()}")
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