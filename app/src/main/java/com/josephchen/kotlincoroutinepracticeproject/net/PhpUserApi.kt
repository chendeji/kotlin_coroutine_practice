package com.josephchen.kotlincoroutinepracticeproject.net

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * ---------------------------------------------------------<br />
 * desc：<br />
 * author：陈德基 <br />
 * date：2022/12/9 15:26<br />
 * email：18701434169@163.com<br />
 * qq: 781571323
 * wx: melody_2009
 * ---------------------------------------------------------<br />
 */
interface PhpUserApi {

    @GET("login/getuserinfo")
    suspend fun getUserInfo(@Query("uid")uid:Int) : APIResult<RUser>?

}