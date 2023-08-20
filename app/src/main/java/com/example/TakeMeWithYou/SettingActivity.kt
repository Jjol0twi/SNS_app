package com.example.TakeMeWithYou

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import java.util.Locale

class SettingActivity : AppCompatActivity() {

    private val settingToolbar: Toolbar by lazy { findViewById(R.id.setting_toolbar) }
    private lateinit var settingMainListView: LinearLayout
    private val settingMainItems: Array<String> = arrayOf("설정", "폰트", "테마", "로그아웃")
    private val settingSubItems: Map<String, Array<String>> = mapOf(
        "설정" to arrayOf("시스템 설정", "한국어", "English"),
        "폰트" to arrayOf("기본", "아직 준비중"),
        "테마" to arrayOf("기본", "준비중"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_activity)
        settingMainListView = findViewById(R.id.setting_main_listview)
        setSupportActionBar(settingToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        initListView()
    }

    private fun initListView() {
        for (i in settingMainItems) {
            val settingView = layoutInflater.inflate(R.layout.setting_listview_item, null)
            val settingText: TextView = settingView.findViewById(R.id.setting_listview_item_text)
            settingText.text = i
            settingMainListView.addView(settingView)
            val settingSubContainer: LinearLayout =
                layoutInflater.inflate(
                    R.layout.setting_listview_container_subitem,
                    null
                ) as LinearLayout
            settingMainListView.addView(settingSubContainer)
            settingView.setOnClickListener {
                if (settingSubItems[i] != null) {
                    if (settingSubContainer.visibility == View.VISIBLE) {
                        settingSubContainer.visibility = View.GONE
                    } else {
                        settingSubContainer.visibility = View.VISIBLE
                        settingSubContainer.removeAllViews()
                        for (j in settingSubItems[i]!!) {
                            val settingChildView =
                                layoutInflater.inflate(R.layout.setting_listview_subitem, null)
                            val settingSubText: TextView =
                                settingChildView.findViewById(R.id.setting_listview_subItem_text)
                            settingSubText.text = j
                            settingSubContainer.addView(settingChildView)
                            settingChildView.setOnClickListener {
                                clickSettingItem(j)
                            }
                        }
                    }

                }

            }
        }
    }

    private fun setLocate(Lang: String) {
        val locale = Locale(Lang)

        Locale.setDefault(locale)

        val config = Configuration()
        config.setLocale(locale)

        val resources = baseContext.resources
        val context = resources.configuration
        context.locale = locale

        resources.updateConfiguration(config, resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }

    private fun clickSettingItem(j: String) {
        if (j == "시스템 설정") {
            val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
            val language = sharedPreferences.getString("My_Lang", "ko")
            if (language != null) {
                setLocate(language)
                recreate()
            }
        }
        if (j == "한국어") {
            setLocate("ko")
            recreate()
        }
        if (j == "영어") {
            setLocate("ko")
            recreate()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.setting_toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}