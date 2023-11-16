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

        val docid =intent.getStringExtra("DocumentID")

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
        //이미지 안쓸거임.

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

            startActivity(intent)

        }

    }


}