package com.example.TakeMeWithYou

import android.content.ClipData.Item
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.TakeMeWithYou.data.PostContentData
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainPageActivity : AppCompatActivity() {
    private lateinit var mainContentListView: LinearLayout
    private val listViewData = PostContentData.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.from_up_enter, R.anim.none)
        setContentView(R.layout.mainpage_activity)
        setCustomToolbar(R.id.mainToolbar)

        mainContentListView = findViewById(R.id.main_content_listView)
        val bottomnavi = findViewById<BottomNavigationView>(R.id.mainPageBottomNavigationView)
        bottomnavi.menu.getItem(1).isChecked = true;
        bottomnavi.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_detail -> {
                    startActivity(Intent(this, DetailPage::class.java))
                    finish()
                    true
                }

                R.id.menu_main -> {
                    startActivity(Intent(this, MainPageActivity::class.java))
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
                listViewData.addLikeCount(listViewItem.indexOf(i))
            }
            mainContentListView.addView(itemView)
        }
    }


    // 메뉴 아이템 툴바에 집어 넣기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_top_toolbar, menu)
        return true
    }

    private fun setCustomToolbar(layout: Int) {
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(layout)
        // 툴바를 액션바로 지정
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        // 액션바에서 앱 이름 보이지 않게 지정
        actionBar?.setDisplayShowCustomEnabled(false)
        toolbar.setOnMenuItemClickListener{item ->
            if (item.itemId == R.id.toolbar_btn_setting){
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
                true
            }
            else{
                false
            }
        }
        }
    }



