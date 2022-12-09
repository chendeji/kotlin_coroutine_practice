package com.josephchen.kotlincoroutinepracticeproject.utils

import okhttp3.internal.Util
import java.io.InputStream
import java.io.OutputStream

/**
 * ---------------------------------------------------------<br />
 * desc：<br />
 * author：陈德基 <br />
 * date：2022/12/9 12:08<br />
 * email：18701434169@163.com<br />
 * qq: 781571323
 * wx: melody_2009
 * ---------------------------------------------------------<br />
 */

inline fun InputStream.copyTo(out: OutputStream,
                              bufferSize:Int = DEFAULT_BUFFER_SIZE, progress:(Long)->Unit):Long {
    var bytesCopied = 0L
    val buffer = ByteArray(bufferSize)
    var bytes = read(buffer)
    while (bytes >= 0) {
        out.write(buffer, 0, bytes)
        bytesCopied += bytes
        bytes = read(buffer)
        // 将下载字节发布出去
        progress(bytesCopied)
    }
    return bytesCopied
}