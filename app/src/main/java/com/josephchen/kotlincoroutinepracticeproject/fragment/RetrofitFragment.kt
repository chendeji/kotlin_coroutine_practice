package com.josephchen.kotlincoroutinepracticeproject.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.josephchen.kotlincoroutinepracticeproject.R
import com.josephchen.kotlincoroutinepracticeproject.databinding.FragmentHomeBinding
import com.josephchen.kotlincoroutinepracticeproject.databinding.FragmentRetrofitBinding
import com.josephchen.kotlincoroutinepracticeproject.viewModel.RetrofitViewModel


class RetrofitFragment : Fragment() {

    private val retrofitViewModel : RetrofitViewModel by viewModels()
    private val binding : FragmentRetrofitBinding by lazy {
        FragmentRetrofitBinding.inflate(layoutInflater)
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

        binding.apply {
            btnRequestApi.setOnClickListener {
                retrofitViewModel.getUserInfo(1234)
            }
        }

        retrofitViewModel.userInfo.observe(viewLifecycleOwner) {
            binding.tvApiResult.text = it.name
        }
    }


}