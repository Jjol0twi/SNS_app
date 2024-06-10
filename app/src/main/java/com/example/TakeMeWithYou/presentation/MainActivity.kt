package com.example.TakeMeWithYou.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.TakeMeWithYou.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() = with(binding) {

    }
}