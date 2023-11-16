package com.example.project1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.QueryDocumentSnapshot

//해야될것. 리스트에서 아이템 클릭시 해당 아이템의 정보가 보이는 페이지.
// 아이템 추가버튼 클릭시 상품을 등록할 페이지 만들기.
// 그 이후에 파이어스토어활용해서. 아이템들 정보 집어넣기 과정.

//
//data class Item(val id: String, val name: String, val price: Int) {
//    constructor(doc: QueryDocumentSnapshot) :
//            this(doc.id, doc["name"].toString(), doc["price"].toString().toIntOrNull() ?: 0)
//    constructor(key: String, map: Map<*, *>) :
//            this(key, map["name"].toString(), map["price"].toString().toIntOrNull() ?: 0)
//}

class ContentsRVAdapter(val items: MutableList<ContentModel>) : RecyclerView.Adapter<ContentsRVAdapter.ViewHolder>() {



    interface ItemClick{
        fun onClick(view: View, position: Int)
    }
    var itemClick : ItemClick? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContentsRVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.contents_item_rv,parent,false)
        return ViewHolder(v)
    }
    override fun onBindViewHolder(holder: ContentsRVAdapter.ViewHolder, position: Int) {
        if(itemClick!=null){
            holder.itemView.setOnClickListener{v->
                itemClick?.onClick(v,position)
            }
        }
        holder.bindItem(items[position])



    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindItem(item: ContentModel){
//            val contentTitle = itemView.findViewById<TextView>(R.id.item_explain_tv)
//            contentTitle.text = item.title

            val contentTitle =itemView.findViewById<TextView>(R.id.listTitle)
            val listPrice = itemView.findViewById<TextView>(R.id.listPrice)
            val listContent = itemView.findViewById<TextView>(R.id.listContent)
//            val listImage = itemView.findViewById<ImageView>(R.id.listImage)

            contentTitle.text= item.listTitle
            listPrice.text = item.listPrice
            listContent.text  = item.listContents


        }
    }
}



