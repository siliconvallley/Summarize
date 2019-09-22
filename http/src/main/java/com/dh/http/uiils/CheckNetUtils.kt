package com.dh.http.uiils

import com.dh.http.exception.ExceptionCode

/**
 * @author 86351
 * @date 2019/5/19
 * @description
 * @version
 */
object CheckNetUtils {

    /**
     * 判断是否需要登录
     */
    fun isLogin(errCode: Int): Boolean {
        return (errCode == ExceptionCode.ERROR_CODE_400
                || errCode == ExceptionCode.ERROR_CODE_401
                || errCode == ExceptionCode.ERROR_CODE_402
                || errCode == ExceptionCode.ERROR_CODE_403
                || errCode == ExceptionCode.ERROR_CODE_405
                || errCode == ExceptionCode.ERROR_CODE_410
                || errCode == ExceptionCode.ERROR_CODE_411
                || errCode == ExceptionCode.ERROR_CODE_414)
    }
}