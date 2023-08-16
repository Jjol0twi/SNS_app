package com.example.TakeMeWithYou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar

class SettingActivity : AppCompatActivity() {

    private val settingToolbar: Toolbar by lazy { findViewById(R.id.setting_toolbar) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_activity)
//        settingToolbar = findViewById(R.id.setting_toolbar)
        setSupportActionBar(settingToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
    }
}