package com.josephchen.kotlincoroutinepracticeproject.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.josephchen.kotlincoroutinepracticeproject.R
import com.josephchen.kotlincoroutinepracticeproject.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private val binding : FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
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
            btnFlowDownload.setOnClickListener{
                findNavController().navigate(R.id.action_homeFragment2_to_downloadFragment2)
            }
            btnFlowRoom.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment2_to_roomFragment)
            }
            btnFlowRetrofit.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment2_to_retrofitFragment)
            }
        }
    }

}