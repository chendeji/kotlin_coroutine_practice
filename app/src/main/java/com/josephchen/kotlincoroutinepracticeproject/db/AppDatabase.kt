package com.josephchen.kotlincoroutinepracticeproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * ---------------------------------------------------------<br />
 * desc：<br />
 * author：陈德基 <br />
 * date：2022/12/9 13:43<br />
 * email：18701434169@163.com<br />
 * qq: 781571323
 * wx: melody_2009
 * ---------------------------------------------------------<br />
 */
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao

    companion object {
        private var instance: AppDatabase? = null
        fun getInstance(cxt: Context):AppDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(cxt, AppDatabase::class.java, "room_practice")
                    .build()
                    .also { instance = it }
            }
        }
    }
}