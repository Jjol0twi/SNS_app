package com.example.TakeMeWithYou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar

class SettingActivity : AppCompatActivity() {

    private val settingToolbar: Toolbar by lazy { findViewById(R.id.setting_toolbar) }
    private val settingMainItems: Array<String> = arrayOf("설정", "폰트", "테마", "로그아웃")
    private val settingSubItems: Map<String, Array<String>> = mapOf(
        "설정" to arrayOf("시스템 설정", "한국어", "English"),
        "폰트" to arrayOf("기본", "아직 준비중"),
        "테마" to arrayOf("기본", "준비중"),
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_activity)
//        settingToolbar = findViewById(R.id.setting_toolbar)
        setSupportActionBar(settingToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.setting_toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}