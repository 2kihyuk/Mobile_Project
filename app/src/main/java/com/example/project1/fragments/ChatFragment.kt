package com.example.project1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.project1.R
import com.example.project1.databinding.FragmentChatBinding
import com.example.project1.databinding.FragmentHomeBinding


class ChatFragment : Fragment() {
    lateinit var binding: FragmentChatBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_chat,container,false)

        binding.hometab.setOnClickListener {
            val navController = Navigation.findNavController(requireView())
            navController.navigate(R.id.action_chatFragment_to_homeFragment)

        }
        binding.settingstab.setOnClickListener {
            val navController = Navigation.findNavController(requireView())
            navController.navigate(R.id.action_chatFragment_to_settingsFragment)
        }
        // Inflate the layout for this fragment
        return binding.root
    }


    }
