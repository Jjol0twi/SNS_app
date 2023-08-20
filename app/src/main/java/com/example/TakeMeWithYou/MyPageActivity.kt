package com.example.TakeMeWithYou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import com.example.TakeMeWithYou.data.PostContentData
import com.google.android.material.bottomnavigation.BottomNavigationView

class MyPageActivity : AppCompatActivity() {
    private lateinit var myPageContentListView: LinearLayout
    private val listViewData = PostContentData.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.from_right_enter, R.anim.none)
        setContentView(R.layout.mypage_activity)
        myPageContentListView = findViewById(R.id.my_page_content_listview)
        val bottomnavi = findViewById<BottomNavigationView>(R.id.my_page_navigation_view)
        bottomnavi.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_detail -> {
                    startActivity(Intent(this, DetailPage::class.java))
                    true
                }
                R.id.menu_main -> {
                    startActivity(Intent(this, MainPageActivity::class.java))
                    true
                }
                R.id.menu_myPage -> {
                    startActivity(Intent(this, MyPageActivity::class.java))
                    true
                }
                else -> false
            }
        }
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