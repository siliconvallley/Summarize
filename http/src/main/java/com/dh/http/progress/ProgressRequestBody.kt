package com.dh.http.progress

import okhttp3.MediaType
import okhttp3.RequestBody
import okio.*

/**
 * @author huidingc
 * @date 2018/11/7
 * @description ProgressRequestBody 封装上传进度的请求体
 * @version
 */
class ProgressRequestBody constructor(private var requestBody: RequestBody,
                                      private var progressListener: ProgressListener
) : RequestBody() {

    private lateinit var bufferedSink: BufferedSink

    override fun contentType(): MediaType? {
        return requestBody.contentType()
    }

    override fun contentLength(): Long {
        return requestBody.contentLength()
    }

    override fun writeTo(sink: BufferedSink) {
        bufferedSink = Okio.buffer(sinks(sink))
        requestBody.writeTo(bufferedSink)
        bufferedSink.flush()
    }

    private fun sinks(sink: BufferedSink): Sink {
        return object : ForwardingSink(sink) {
            //当前写入字节数
            var bytesWrite = 0L
            //总字节长度，避免多次调用contentLength()方法
            var totalBytesWrite = 0L

            override fun write(source: Buffer, byteCount: Long) {
                super.write(source, byteCount)
                //增加当前写入的字节数
                bytesWrite += byteCount
                if (totalBytesWrite == 0L) {
                    totalBytesWrite = contentLength()
                }
                progressListener.onProgress(bytesWrite, totalBytesWrite, bytesWrite == totalBytesWrite)
            }
        }

    }
}