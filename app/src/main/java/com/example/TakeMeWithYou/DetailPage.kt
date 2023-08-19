package com.example.TakeMeWithYou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ListView
import androidx.appcompat.widget.Toolbar
import com.example.TakeMeWithYou.adapter.DetailListViewAdapter
import com.example.TakeMeWithYou.adapter.MainListViewAdapter
import com.example.TakeMeWithYou.adapter.PostContentData

class DetailPage : AppCompatActivity() {
    private lateinit var detailContentListView : ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_page_activity)
        setCustomToolbar(R.id.toolbar)
        val listViewItem = PostContentData.getInstance()
        detailContentListView = findViewById(R.id.detail_content_listview)
        detailContentListView.adapter = DetailListViewAdapter(this,listViewItem.getAllItem())
    }

    // 메뉴 아이템 툴바에 집어 넣기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu,menu)
        return true
    }
    private fun setCustomToolbar(layout: Int){
        val toolbar = findViewById<Toolbar>(layout)
        // 툴바를 액션바로 지정
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        // 액션바에서 앱 이름 보이지 않게 지정
        actionBar?.setDisplayShowCustomEnabled(false)
    }
}
