package com.example.TakeMeWithYou

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Postpageadd : AppCompatActivity() {
    lateinit var postpageFinish : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.postpage_activity)
        postpageFinish=findViewById(R.id.imageButton)
        postpageFinish.setOnClickListener { finish() }
    }
}