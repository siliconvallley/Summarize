package com.dh.http.exception

/**
 * @author huidingc
 * @date 2018/11/5
 * @description ExceptionCode 异常码
 */
object ExceptionCode{
    /**
     * 网络错误
     */
    const val NETWORK_ERROR = 0x10
    const val NETWORK_ERROR_MSG = "当前网络不可用"
    /**
     * Http错误
     */
    const val HTTP_ERROR = 0x20
    const val HTTP_ERROR_MSG = "您的网络状况不佳，请检查网络连接"
    const val CONNECT_ERROR_MSG = "服务器连接失败"
    /**
     * json解析异常
     */
    const val JSON_ERROR = 0x30
    const val JSON_ERROR_MSG = "无法解析的内容"
    /**
     * 未知异常
     */
    const val UNKNOWN_ERROR = 0x40
    const val UNKNOWN_ERROR_MSG = "未知错误"
    /**
     * 运行时异常-包含自定义异常
     */
    const val RUNTIME_ERROR = 0x50
    /**
     * 域名解析异常
     */
    const val UNKNOWNHOST_ERROR = 0x60
    const val UNKNOWNHOST_ERROR_MSG = "无法解析的域名"
    /**
     * 未设置支付密码
     */
    const val NO_PASSWORD_CODE: Int = 1000
    /**
     * 后台定义普通错误码
     */
    const val NORMAL_ERROR_CODE: Int = 100

    // 服务器的一些关于登录的错误码
    const val ERROR_CODE_400 = 400
    const val ERROR_CODE_401 = 401
    const val ERROR_CODE_402 = 402
    const val ERROR_CODE_403 = 403
    const val ERROR_CODE_405 = 405
    const val ERROR_CODE_410 = 410
    const val ERROR_CODE_411 = 411
    const val ERROR_CODE_414 = 414

    /*NEED_LOGIN(400, "还未登录，需要登录"),

    EXPIRED(401, "登录已经过期"),

    LOGIN_ON_OTHER_DEVICE(402, "在其它设备登录，被挤下线了"),

    PERMISSION_FORBIDDEN(403, "没有权限"),

    TOKEN_VALIDATE_FAILED(405, "token验证失败"),

    ACCOUNT_NOT_EXIST(410, "用户不存在"),

    PASSWORD_ERROR(411, "密码错误"),

    DEVICE_UNAVAILABLE(414, "非法设备"),;*/
}
