package com.josephchen.kotlincoroutinepracticeproject.download

import java.io.File

/**
 * ---------------------------------------------------------<br />
 * desc：<br />
 * author：陈德基 <br />
 * date：2022/12/9 11:56<br />
 * email：18701434169@163.com<br />
 * qq: 781571323
 * wx: melody_2009
 * ---------------------------------------------------------<br />
 */
sealed class DownloadStatus {
    data class Progress(val value: Int) : DownloadStatus()
    data class Error(val throwable: Throwable) : DownloadStatus()
    data class Done(val file:File) : DownloadStatus()
    object None : DownloadStatus()
}
