package com.example.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.project1.databinding.ActivityEditProductBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class EditProductActivity : AppCompatActivity() {
    private val db: FirebaseFirestore = Firebase.firestore
    private val itemsCollectionRef = db.collection("Item") // items는 Collection ID
    lateinit var binding:ActivityEditProductBinding
    lateinit var selectedItemID: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("EditTitle")
        val price = intent.getStringExtra("EditPrice")
        val content = intent.getStringExtra("EditContent")
        selectedItemID = intent.getStringExtra("DocId") ?: ""

        binding.titleEditText.setText(title)
        binding.priceEditText.setText(price)
        binding.ContentEditText.setText(content) //이 부분 잘안됨.


        binding.EditButton.setOnClickListener {
            val updateTitle = binding.titleEditText.text.toString()
            val updatePrice = binding.priceEditText.text.toString()
            val updateContent = binding.ContentEditText.text.toString()
            val selectedItemRef = FirebaseFirestore.getInstance().collection("Item").document(selectedItemID!!)



            selectedItemRef.update(
                mapOf(
                    "itemtitle" to updateTitle,
                    "itemprice" to updatePrice,
                    "itemcontent" to updateContent
                )
            ).addOnSuccessListener {
                Log.d("EditItem", "Item Update on Firestore Success")

                // 업데이트가 성공하면 메인 화면으로 이동
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Log.d("EditItem", "Item Update on Firestore Fail")
            }
        }


        binding.backButton.setOnClickListener {
            val intent = Intent(this,ListItem::class.java)
            startActivity(intent)
            finish()
        }


    }
}