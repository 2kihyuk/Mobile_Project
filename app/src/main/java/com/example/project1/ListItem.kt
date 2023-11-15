package com.example.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.project1.databinding.ActivityListItemBinding

class ListItem : AppCompatActivity() {

     lateinit var binding: ActivityListItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("listTitle")
        val content = intent.getStringExtra("listContents")
        val price = intent.getStringExtra("listPrice")
//        val image = intent.getStringExtra("listImage")


        Log.d("check",title.toString())
        Log.d("check",content.toString())
        Log.d("check",price.toString())

        binding.detailTitle.text=title
        binding.price.text=price +"원"
        binding.detailContent.text=content
        //상품의 이미지는 어떻게 받아와야함? 추후에는 이 과정들 모두 파이어베이스 파이어스토어에 저장한 값을 읽어와서 변환하는 과정으로 바꿔야함.


        binding.backButton.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


}