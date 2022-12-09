package com.josephchen.kotlincoroutinepracticeproject.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.josephchen.kotlincoroutinepracticeproject.R
import com.josephchen.kotlincoroutinepracticeproject.adapter.UserAdapter
import com.josephchen.kotlincoroutinepracticeproject.databinding.FragmentHomeBinding
import com.josephchen.kotlincoroutinepracticeproject.databinding.FragmentRoomBinding
import com.josephchen.kotlincoroutinepracticeproject.db.User
import com.josephchen.kotlincoroutinepracticeproject.viewModel.UserViewModel
import kotlinx.coroutines.flow.collect


class RoomFragment : Fragment() {

    private val userViewModel : UserViewModel by viewModels()
    private lateinit var adapter : UserAdapter
    private val binding : FragmentRoomBinding by lazy {
        FragmentRoomBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.apply {
            adapter = UserAdapter(this)
            binding.rvUserList.adapter = adapter
            lifecycleScope.launchWhenCreated {
                userViewModel.getAllUser().collect{ users ->
                    adapter.setUserList(users)
                }
            }
        }

        binding.apply {
            btnAddUser.setOnClickListener {
                val id = etUserId.text.toString().toInt()
                val name = etUserName.text.toString()
                val age = etUserAge.text.toString().toInt()
                userViewModel.inseatUser(id, name, age)
            }
        }
    }
}