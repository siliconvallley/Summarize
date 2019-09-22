package com.dh.http.progress

import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.*

/**
 * @author huidingc
 * @date 2018/11/7
 * @description ProgressResponseBody 封装下载响应体
 * @version
 */
class ProgressResponseBody constructor(private var responseBody: ResponseBody,
                                       private var progressListener: ProgressListener
):ResponseBody() {

    private lateinit var bufferedSource: BufferedSource

    override fun contentLength(): Long {
        return responseBody.contentLength()
    }

    override fun contentType(): MediaType? {
        return responseBody.contentType()
    }

    override fun source(): BufferedSource {
        bufferedSource = Okio.buffer(sources(responseBody.source()))
        return bufferedSource
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    private fun sources(source: BufferedSource?): Source {
        return object :ForwardingSource(source){
            var totalBytesRead = 0L

            override fun read(sink: Buffer, byteCount: Long): Long {
                val bytesRead = super.read(sink, byteCount)
                totalBytesRead += if (bytesRead != -1L) bytesRead else 0
                progressListener.onProgress(totalBytesRead,contentLength(),bytesRead == -1L)
                return bytesRead
            }
        }
    }
}