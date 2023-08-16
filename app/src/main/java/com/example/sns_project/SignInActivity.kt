package com.example.sns_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.sns_project.databinding.ActivityMainBinding
import fragment.SignInFragment
import fragment.SignUpFragment

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val fragmentManager = supportFragmentManager

        fragmentManager.beginTransaction().replace(R.id.fragment_menu, SignInFragment()).commit()

        val select_signin = findViewById<Button>(R.id.signinbtn)
        val select_signup = findViewById<Button>(R.id.signupbtn)

        select_signin.setOnClickListener {
            fragmentManager.beginTransaction().replace(R.id.fragment_menu, SignInFragment()).commit()
            select_signin.backgroundTintList = getColorStateList(R.color.white)
            select_signup.backgroundTintList = getColorStateList(R.color.light_gray)
        }

        select_signup.setOnClickListener {
            fragmentManager.beginTransaction().replace(R.id.fragment_menu, SignUpFragment()).commit()
            select_signin.backgroundTintList = getColorStateList(R.color.light_gray)
            select_signup.backgroundTintList = getColorStateList(R.color.white)
        }
    }
}