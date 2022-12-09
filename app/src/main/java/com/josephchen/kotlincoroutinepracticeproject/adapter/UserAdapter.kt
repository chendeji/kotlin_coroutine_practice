package com.josephchen.kotlincoroutinepracticeproject.adapter

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.josephchen.kotlincoroutinepracticeproject.databinding.ItemRoomUserLayoutBinding
import com.josephchen.kotlincoroutinepracticeproject.db.User

/**
 * ---------------------------------------------------------<br />
 * desc：<br />
 * author：陈德基 <br />
 * date：2022/12/9 14:19<br />
 * email：18701434169@163.com<br />
 * qq: 781571323
 * wx: melody_2009
 * ---------------------------------------------------------<br />
 */
class UserAdapter(private val cxt:Context) : RecyclerView.Adapter<UserViewHolder>() {

    private val users = ArrayList<User>()
    fun setUserList(users : List<User>) {
        this.users.clear()
        this.users.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        var binding = ItemRoomUserLayoutBinding.inflate(LayoutInflater.from(cxt), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = users[position]
        var binding = holder.binding as ItemRoomUserLayoutBinding
        binding.apply {
            tvUserId.text = item.id.toString()
            tvUserName.text = item.name
            tvUserAge.text = item.age.toString()
        }

    }

    override fun getItemCount(): Int = users.size

}