package com.josephchen.kotlincoroutinepracticeproject.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.josephchen.kotlincoroutinepracticeproject.db.User
import com.josephchen.kotlincoroutinepracticeproject.net.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

/**
 * ---------------------------------------------------------<br />
 * desc：<br />
 * author：陈德基 <br />
 * date：2022/12/9 16:19<br />
 * email：18701434169@163.com<br />
 * qq: 781571323
 * wx: melody_2009
 * ---------------------------------------------------------<br />
 */
class RetrofitViewModel(app:Application) : AndroidViewModel(app) {

    val userInfo = MutableLiveData<User>()

    fun getUserInfo(id:Int) = viewModelScope.launch {
        flow {
            val remoteUserInfo = RetrofitClient.userApi.getUserInfo(id)
            remoteUserInfo?.takeIf {
                it.status == 1
            }?.let {
                it.result
            }?.apply {
                var uiUser = User(uid, name, age)
                emit(uiUser)
            }
        }.catch {
            Log.e("getUserInfo", it.message ?: "接口访问错误")
        }.flowOn(Dispatchers.IO).collect{
            userInfo.value = it
        }
    }

}