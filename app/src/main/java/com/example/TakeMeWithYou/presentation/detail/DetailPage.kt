package com.example.TakeMeWithYou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.TakeMeWithYou.data.PostContentData
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetailPage : AppCompatActivity() {
    private lateinit var detailContentListView: LinearLayout
    private val listViewData = PostContentData.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.from_left_enter, R.anim.none)
        setContentView(R.layout.detail_page_activity)
        setCustomToolbar(R.id.toolbar)
        detailContentListView = findViewById(R.id.detail_content_listview)
        initLisView()

        val bottomnavi = findViewById<BottomNavigationView>(R.id.bn_)
        bottomnavi.menu.getItem(0).isChecked = true;
        bottomnavi.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_detail -> {
                    startActivity(Intent(this, DetailPage::class.java))
                    true
                }

                R.id.menu_main -> {
                    startActivity(Intent(this, MainpageActivity::class.java))
                    finish()
                    true
                }

                R.id.menu_myPage -> {
                    startActivity(Intent(this, MyPageActivity::class.java))
                    finish()
                    true
                }

                else -> false
            }
        }
    }

    private fun initLisView() {
        val listViewItem = listViewData.getAllItem()
        for (i in listViewItem) {
            val itemView = LayoutInflater.from(this)
                .inflate(R.layout.detail_listview_item, null)   // item layout 가져오기
            val itemViewImageView: ImageView =
                itemView.findViewById(R.id.d_post_img)  // item widget findviewbyid를 통해서 호출
            val itemViewDesView: TextView = itemView.findViewById(R.id.d_post_des)
            val itemViewLikeCount: TextView =
                itemView.findViewById(R.id.d_post_like_count)
            val itemViewLikeButton: ImageButton =
                itemView.findViewById(R.id.d_post_btn_like)
            itemViewImageView.setImageResource(i.imagePath)
            itemViewDesView.text = i.description
            itemViewLikeCount.text = i.likeCount.toString()
            itemViewLikeButton.setOnClickListener {
                listViewData.addLikeCount(listViewItem.indexOf(i))
            }
            detailContentListView.addView(itemView)
        }
    }

    // 메뉴 아이템 툴바에 집어 넣기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    private fun setCustomToolbar(layout: Int) {
        val toolbar = findViewById<Toolbar>(layout)
        // 툴바를 액션바로 지정
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        // 액션바에서 앱 이름 보이지 않게 지정
        actionBar?.setDisplayShowCustomEnabled(false)
    }
}
