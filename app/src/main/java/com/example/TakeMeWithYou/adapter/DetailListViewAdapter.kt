package com.example.TakeMeWithYou.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.TakeMeWithYou.R
import com.example.TakeMeWithYou.model.PostContentModel

class DetailListViewAdapter(
    private val context: Context,
    private val itemList: ArrayList<PostContentModel>
) :
    BaseAdapter() {
    private val listViewItem = PostContentData.getInstance()

    override fun getCount(): Int = itemList.size

    override fun getItem(position: Int): Any = itemList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.detail_listview_item,
            parent,
            false
        )
        val itemImageView: ImageView = itemView.findViewById(R.id.detail_post_content_img)
        val itemDesView: TextView = itemView.findViewById(R.id.detail_post_content_des)
        val itemCountView: TextView = itemView.findViewById(R.id.detail_post_content_like_count)
        val itemLikeButton: ImageButton = itemView.findViewById(R.id.detail_post_content_like)
        itemLikeButton.setOnClickListener {
            Toast.makeText(parent?.context, "wow", Toast.LENGTH_SHORT).show()
            if (itemLikeButton.tag == R.drawable.unfavorite_icon) {
                itemLikeButton.setImageResource(R.drawable.baseline_favorite_24)
                listViewItem.addLikeCount(position)
                notifyDataSetChanged()
            } else {
                itemLikeButton.setImageResource(R.drawable.unfavorite_icon)
                listViewItem.subLikeCount(position)
                notifyDataSetChanged()
            }
        }
        itemImageView.setImageResource(itemList[position].imagePath)
        itemDesView.text = itemList[position].description
        itemCountView.text = itemList[position].likeCount.toString()
        return itemView
    }
}