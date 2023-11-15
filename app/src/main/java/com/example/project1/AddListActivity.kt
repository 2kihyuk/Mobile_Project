package com.example.project1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.project1.databinding.ActivityAddListBinding
import com.example.project1.fragments.HomeFragment
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class AddListActivity : AppCompatActivity() { //새로운 상품 등록.
    private val db: FirebaseFirestore = Firebase.firestore
    lateinit var binding : ActivityAddListBinding
    private val itemsCollectionRef = db.collection("Item") // items는 Collection ID
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.addButton.setOnClickListener {

            val title = binding.titleEditText.text.toString()
            val price = binding.priceEditText.text.toString()
            val content = binding.ContentEditText.text.toString()

            val Item = hashMapOf(
                "itemtitle" to title,
                "itemprice" to price,
                "itemcontent" to content
            )
            itemsCollectionRef.add(Item)
                .addOnSuccessListener {
                    Log.d("AddItem","Item Add on FireStore Success")
                }
                .addOnFailureListener {
                    Log.d("AddItem","Item Add on FireStore Fail")
                }
            Log.d("add",title)
//
//            //여기에 새로운 등록 상품에 대한 정보를 파이어스토어에 저장.
              //

            val intent = Intent(this,MainActivity::class.java)

            startActivity(intent)
        }

        binding.backButton.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}