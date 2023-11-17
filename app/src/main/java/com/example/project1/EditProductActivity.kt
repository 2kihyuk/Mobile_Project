package com.example.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.project1.databinding.ActivityEditProductBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore


/*
해야 할 것 .
1. 상품 수정 기능은 구현 완료. 그런데 텍스트 이상하게 나오는거 수정해야하고.
2. 글을 등록한 사용자만 상품을 수정할 수 있게 해야하고.
3. 판매여부에 따른 필터링 기능 구현.
4. 채팅 기능 구현.  => 경미
5. 상품 들어갔을때 , 판매자와 판매여부는 작성자 계정과 판매중 상태로 하여 저장.
 */

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

            itemsCollectionRef.document(selectedItemID).update("itemtitle",updateTitle)
            itemsCollectionRef.document(selectedItemID).update("itemprice",updatePrice)
            itemsCollectionRef.document(selectedItemID).update("itemcontent",updateContent)
            val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

        }


        binding.backButton.setOnClickListener {
            val intent = Intent(this,ListItem::class.java)
            startActivity(intent)
            finish()
        }


    }
}