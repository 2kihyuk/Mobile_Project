package com.example.project1.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.ContentModel
//import com.example.project1.ContentListActivity
import com.example.project1.ContentsRVAdapter
import com.example.project1.ListItem
import com.example.project1.R
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

        val rv : RecyclerView = binding.rv
        val items = ArrayList<ContentModel>()
        items.add(ContentModel("선풍기",R.drawable.sample1,"산지 한달 정도 된 선풍기 입니다. 싸게 내놓았으니 가져가세요!!!!","35000원"))
        items.add(ContentModel("인형",R.drawable.sample2,"인형입니다. 추억이 깃든 조나단 인형입니다. 가져가세요!","15000원"))
        items.add(ContentModel("기름",R.drawable.sample3,"산지 한달 정도 된 선풍기 입니다. 싸게 내놓았으니 가져가세요!!!!","233000원"))
        items.add(ContentModel("자동차",R.drawable.sample4,"산지 한달 정도 된 선풍기 입니다. 싸게 내놓았으니 가져가세요!!!!","3231000원"))
        items.add(ContentModel("자동차",R.drawable.sample4,"산지 한달 정도 된 선풍기 입니다. 싸게 내놓았으니 가져가세요!!!!","3231000원"))
        items.add(ContentModel("자동차",R.drawable.sample4,"산지 한달 정도 된 선풍기 입니다. 싸게 내놓았으니 가져가세요!!!!","3231000원"))
        items.add(ContentModel("자동차",R.drawable.sample4,"산지 한달 정도 된 선풍기 입니다. 싸게 내놓았으니 가져가세요!!!!","3231000원"))


        val rvAdapter = ContentsRVAdapter(items)
        rv.adapter= rvAdapter

        rv.layoutManager = LinearLayoutManager(context)

        rvAdapter.itemClick = object :ContentsRVAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {
                Toast.makeText(context,items[position].listTitle,Toast.LENGTH_SHORT).show()
//                val navController = Navigation.findNavController(requireView())
//                navController.navigate(R.id.action_homeFragment_to_listItem)

                val intent = Intent(context,ListItem::class.java)
                context?.startActivity(intent)


            }

        }




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
