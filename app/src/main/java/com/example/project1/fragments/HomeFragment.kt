package com.example.project1.fragments

import android.app.Activity
import android.app.AlertDialog
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
    private var selectedItemID: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val db = Firebase.firestore

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        val rv : RecyclerView = binding.rv

        val items = mutableListOf<ContentModel>()





        val rvAdapter = ContentsRVAdapter(items)
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(context) // 리싸이클러뷰에 아이템 연결 .

//데이터베이스에서 등록된 상품 가지고 아이템 상품 목록에 띄우기 완성.
        itemsCollectionRef.get().addOnSuccessListener { querySnapshot ->
            for (doc in querySnapshot) {
                val listTitle = doc.getString("itemtitle") ?: ""
                // val listImage = doc.getInt("listImage") // 이미지는 필요에 따라 추가
                val listContents = doc.getString("itemcontent") ?: ""
                val listPrice = doc.getString("itemprice") ?: ""

                val contentModel = ContentModel(listTitle, listContents, listPrice,doc.id)
                items.add(contentModel)

                Log.d("itemcheck", listTitle)
                Log.d("itemcheck", listContents)
                Log.d("itemcheck", listPrice)
                Log.d("itemcheck", items.toString())

            }
            rvAdapter.notifyDataSetChanged()
        }.addOnFailureListener{
            exception->Log.w("Firestore", "Error getting documents: ", exception)

        }








        rvAdapter.itemClick = object :ContentsRVAdapter.ItemClick{ //아이템 클릭시. 데이터 가지고 해당 상품 페이지로 이동.
            override fun onClick(view: View, position: Int) {
                Toast.makeText(context,items[position].listTitle,Toast.LENGTH_SHORT).show()

                selectedItemID = items[position].documentID
                val intent = Intent(context,ListItem::class.java)
                intent.putExtra("listTitle",items[position].listTitle)
                intent.putExtra("listContents",items[position].listContents)
                intent.putExtra("listPrice",items[position].listPrice)

                Log.d("check",selectedItemID.toString())
//                intent.putExtra("listImage",items[position].listImage)

                context?.startActivity(intent)

            }

        }







                    //상품 추가 버튼 클릭시 상품 추가하는 액티비티로 이동.
                binding.addListBtn.setOnClickListener {
                    val intent = Intent(context, AddListActivity::class.java)
                    intent.putExtra("DocumentID",selectedItemID)
                    startActivity(intent)

                }
                    //하단바의 설정 탭 클릭시 설정 페이지로 이동
                binding.settingstab.setOnClickListener {
                    val navController = Navigation.findNavController(requireView())
                    navController.navigate(R.id.action_homeFragment_to_settingsFragment)


                }
                    //하단바의 채팅탭 클릭시 내가 받은 메시지 띄우는 페이지로 이동
                binding.chattab.setOnClickListener {
                    val navController = Navigation.findNavController(requireView())
                    navController.navigate(R.id.action_homeFragment_to_chatFragment)
                }
                // Inflate the layout for this fragment
                return binding.root
            }


        }



