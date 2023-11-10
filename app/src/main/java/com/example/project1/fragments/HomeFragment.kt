package com.example.project1.fragments

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.project1.R
import com.example.project1.databinding.ActivityJoinBinding
import com.example.project1.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        binding.settingstab.setOnClickListener {
            val navController = Navigation.findNavController(requireView())
            navController.navigate(R.id.action_homeFragment_to_settingsFragment)

        }
        binding.chattab.setOnClickListener {
            val navController = Navigation.findNavController(requireView())
            navController.navigate(R.id.action_homeFragment_to_chatFragment)
        }
        // Inflate the layout for this fragment
        return binding.root
    }


    }
