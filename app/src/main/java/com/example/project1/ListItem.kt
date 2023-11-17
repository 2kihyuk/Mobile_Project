package com.example.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.project1.databinding.ActivityListItemBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class ListItem : AppCompatActivity() {

     lateinit var binding: ActivityListItemBinding
    private val db: FirebaseFirestore = Firebase.firestore
    private val itemsCollectionRef = db.collection("Item") // items는 Collection ID
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val docid =intent.getStringExtra("DocId")

        Log.d("DocumentCheck",docid.toString())

        val title = intent.getStringExtra("listTitle")
        val content = intent.getStringExtra("listContents")
        val price = intent.getStringExtra("listPrice")



        Log.d("check",title.toString())
        Log.d("check",content.toString())
        Log.d("check",price.toString())

        binding.detailTitle.text=title
        binding.price.text=price +"원"
        binding.detailContent.text=content


        binding.backButton.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        //상품 수정 버튼 클릭시, EditProductActivity를 띄우면서, 지금 가지고 있는 정보들을 보내서, EditProductActivity에서는 인텐트 정보를 받아서 띄워놓고 시작.
        binding.Editbutton.setOnClickListener {
            val intent = Intent(this,EditProductActivity::class.java)
            intent.putExtra("EditTitle",binding.detailTitle.text.toString())
            intent.putExtra("EditPrice",binding.price.text.toString())
            intent.putExtra("EditContent",binding.detailContent.toString())
            intent.putExtra("DocId",docid)
            if (docid != null) {
                Log.d("DocId",docid)
            }

            startActivity(intent)

        }

    }


}