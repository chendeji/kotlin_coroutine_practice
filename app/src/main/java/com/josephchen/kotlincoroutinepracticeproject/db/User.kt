package com.josephchen.kotlincoroutinepracticeproject.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * ---------------------------------------------------------<br />
 * desc：<br />
 * author：陈德基 <br />
 * date：2022/12/9 13:44<br />
 * email：18701434169@163.com<br />
 * qq: 781571323
 * wx: melody_2009
 * ---------------------------------------------------------<br />
 */
@Entity
data class User(
    @PrimaryKey
    val id : Int,
    @ColumnInfo(name = "name")
    val name : String,
    @ColumnInfo(name = "age")
    val age : Int
)
