package com.example.TakeMeWithYou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.TakeMeWithYou.adapter.MainListViewAdapter
import com.example.TakeMeWithYou.adapter.PostContentData

class MainPageActivity : AppCompatActivity() {
    private lateinit var mainContentListView : ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainpage_activity)
        val listViewItem = PostContentData.getInstance()
        mainContentListView = findViewById(R.id.main_content_listView)
        mainContentListView.adapter = MainListViewAdapter(this,listViewItem.getAllItem())
    }
}


