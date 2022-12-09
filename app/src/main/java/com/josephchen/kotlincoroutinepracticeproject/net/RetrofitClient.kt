package com.josephchen.kotlincoroutinepracticeproject.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * ---------------------------------------------------------<br />
 * desc：<br />
 * author：陈德基 <br />
 * date：2022/12/9 15:23<br />
 * email：18701434169@163.com<br />
 * qq: 781571323
 * wx: melody_2009
 * ---------------------------------------------------------<br />
 */
object RetrofitClient {
    val instance by lazy {
        Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .baseUrl("http://192.168.1.97:8000/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val userApi by lazy {
        instance.create(PhpUserApi::class.java)
    }
}