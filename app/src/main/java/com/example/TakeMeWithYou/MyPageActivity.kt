package com.example.TakeMeWithYou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import com.example.TakeMeWithYou.data.PostContentData

class MyPageActivity : AppCompatActivity() {
    private lateinit var myPageContentListView: LinearLayout
    private val listViewData = PostContentData.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage_activity)
        myPageContentListView = findViewById(R.id.my_page_content_listview)
        initLisView()
    }

    private fun initLisView() {
        val listViewItem = listViewData.getAllItem()
        for (i in listViewItem) {
            val itemView = LayoutInflater.from(this).inflate(R.layout.main_listview_item, null)
            val itemViewImageView: ImageView = itemView.findViewById(R.id.main_post_content_img)
            val itemViewIdView: TextView = itemView.findViewById(R.id.main_post_content_id)
            val itemViewLikeCount: TextView =
                itemView.findViewById(R.id.main_post_content_like_count)
            val itemViewLikeButton: ImageButton = itemView.findViewById(R.id.main_post_content_like)
            itemViewImageView.setImageResource(i.imagePath)
            itemViewIdView.text = i.userId
            itemViewLikeCount.text = i.likeCount.toString()
            itemViewLikeButton.setOnClickListener {
            }
            myPageContentListView.addView(itemView)
        }
    }
}