package com.dh.http.progress

/**
 * @author huidingc
 * @date 2018/11/7
 * @description ProgressListener
 * @version
 */
interface ProgressListener {
    /**
     * @param bytesRead 已经读取的长度
     * @param contentLength 文件总长度
     * @param done 是否已经完成
     */
    fun onProgress(bytesRead:Long,contentLength:Long,done:Boolean)

}