package com.example.project1.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.AddListActivity
import com.example.project1.ContentModel
//import com.example.project1.ContentListActivity
import com.example.project1.ContentsRVAdapter
import com.example.project1.ListItem
import com.example.project1.R
import com.example.project1.databinding.FragmentHomeBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore


class HomeFragment : Fragment() {
    private var adapter: ContentsRVAdapter? = null

    private val db: FirebaseFirestore = Firebase.firestore
    private val itemsCollectionRef = db.collection("Item") // items는 Collection ID
    lateinit var binding: FragmentHomeBinding
    private lateinit var rvAdapter: ContentsRVAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        val rv : RecyclerView = binding.rv

        val items = mutableListOf<ContentModel>()


        items.add(ContentModel("선풍기","산지 한달 정도 된 선풍기 입니다. 싸게 내놓았으니 가져가세요!!!!","35000"))
//        items.add(ContentModel("인형","인형입니다. 추억이 깃든 조나단 인형입니다. 가져가세요!","15000원"))
//        items.add(ContentModel("기름","산지 한달 정도 된 선풍기 입니다. 싸게 내놓았으니 가져가세요!!!!","233000원"))
//        items.add(ContentModel("자동차","산지 한달 정도 된 선풍기 입니다. 싸게 내놓았으니 가져가세요!!!!","3231000원"))
//        items.add(ContentModel("자동차","산지 한달 정도 된 선풍기 입니다. 싸게 내놓았으니 가져가세요!!!!","3231000원"))
//        items.add(ContentModel("자동차","산지 한달 정도 된 선풍기 입니다. 싸게 내놓았으니 가져가세요!!!!","3231000원"))
//        items.add(ContentModel("자동차","산지 한달 정도 된 선풍기 입니다. 싸게 내놓았으니 가져가세요!!!!","3231000원"))


        val rvAdapter = ContentsRVAdapter(items)
        rv.adapter= rvAdapter

        rv.layoutManager = LinearLayoutManager(context)



        val db = Firebase.firestore
        itemsCollectionRef.get().addOnSuccessListener {
            for (doc in it) {
                items.add(ContentModel())
                Log.d("itemcheck",items.toString())// Item의 생성자가 doc를 받아 처리
            }

        }



        rvAdapter.itemClick = object :ContentsRVAdapter.ItemClick{ //아이템 클릭시. 데이터 가지고 해당 상품 페이지로 이동.
            override fun onClick(view: View, position: Int) {
                Toast.makeText(context,items[position].listTitle,Toast.LENGTH_SHORT).show()
//                val navController = Navigation.findNavController(requireView())
//                navController.navigate(R.id.action_homeFragment_to_listItem)

                val intent = Intent(context,ListItem::class.java)
                intent.putExtra("listTitle",items[position].listTitle)
                intent.putExtra("listContents",items[position].listContents)
                intent.putExtra("listPrice",items[position].listPrice)
//                intent.putExtra("listImage",items[position].listImage)

                context?.startActivity(intent)

            }

        }



        binding.addListBtn.setOnClickListener {
            val intent = Intent(context, AddListActivity::class.java)
            startActivity(intent)

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
