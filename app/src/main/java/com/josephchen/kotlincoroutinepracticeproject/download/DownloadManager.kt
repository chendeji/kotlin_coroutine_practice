package com.josephchen.kotlincoroutinepracticeproject.download

import android.accounts.NetworkErrorException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import com.josephchen.kotlincoroutinepracticeproject.utils.copyTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

/**
 * ---------------------------------------------------------<br />
 * desc：<br />
 * author：陈德基 <br />
 * date：2022/12/9 11:55<br />
 * email：18701434169@163.com<br />
 * qq: 781571323
 * wx: melody_2009
 * ---------------------------------------------------------<br />
 */
object DownloadManager {
    fun download(url:String, file:File) : Flow<DownloadStatus> {
        return flow {
            var request = Request.Builder().url(url).get().build()
            val response = OkHttpClient.Builder().build().newCall(request).execute()
            if (response.isSuccessful) {
                response.body()?.let { body ->
                    val total = body.contentLength()
                    file.outputStream().use { output ->
                        val input = body.byteStream()
                        var emittedProgress = 0L
                        input.copyTo(output) { bytesCopied ->
                            val process = bytesCopied * 100 / total
                            if (process - emittedProgress > 5) {
                                delay(100)
                                emit(DownloadStatus.Progress(process.toInt())) // 发布下载进度
                                emittedProgress = process
                            }
                        }
                    }
                } ?: throw NetworkErrorException()
                emit(DownloadStatus.Done(file)) // 发布下载完成
            } else {
                throw IOException(response.toString())
            }
        }.catch {
            file.delete()
            emit(DownloadStatus.Error(it))  // 发布下载错误
        }.flowOn(Dispatchers.IO) // 指定流中执行的代码调度在子线程下
    }
}