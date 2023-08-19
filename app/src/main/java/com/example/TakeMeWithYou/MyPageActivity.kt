package com.example.TakeMeWithYou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.TakeMeWithYou.adapter.MainListViewAdapter
import com.example.TakeMeWithYou.adapter.PostContentData

class MyPageActivity : AppCompatActivity() {
    private lateinit var myPageContentListView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage_activity)
        val listViewItem = PostContentData.getInstance()
        myPageContentListView = findViewById(R.id.my_page_content_listview)
        myPageContentListView.adapter = MainListViewAdapter(this,listViewItem.getAllItem())
    }
}