package com.josephchen.kotlincoroutinepracticeproject.net

/**
 * ---------------------------------------------------------<br />
 * desc：<br />
 * author：陈德基 <br />
 * date：2022/12/9 16:17<br />
 * email：18701434169@163.com<br />
 * qq: 781571323
 * wx: melody_2009
 * ---------------------------------------------------------<br />
 */
data class APIResult<T>(
    val status:Int,
    val message:String,
    val result:T
)