package com.josephchen.kotlincoroutinepracticeproject.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.josephchen.kotlincoroutinepracticeproject.db.AppDatabase
import com.josephchen.kotlincoroutinepracticeproject.db.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

/**
 * ---------------------------------------------------------<br />
 * desc：<br />
 * author：陈德基 <br />
 * date：2022/12/9 14:00<br />
 * email：18701434169@163.com<br />
 * qq: 781571323
 * wx: melody_2009
 * ---------------------------------------------------------<br />
 */
class UserViewModel(app:Application) : AndroidViewModel(app) {
    fun inseatUser(uid:Int, name:String, age: Int) = viewModelScope.launch {
        val user = User(id = uid, name = name, age = age)
        AppDatabase
            .getInstance(getApplication())
            .userDao()
            .insertUser(user)
    }

    fun getAllUser() : Flow<List<User>> = AppDatabase
        .getInstance(getApplication())
        .userDao()
        .getAllUsers()
        .catch { Log.e("getAllUser", it.message ?: "未知错误") }
        .flowOn(Dispatchers.IO)
}