package com.example.TakeMeWithYou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.commit
import fragment.SignInFragment
import fragment.SignUpFragment

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_activity)

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

    // 로그인 성공 시
    fun loginSuccess() {
        val intent = Intent(this, MainPageActivity::class.java)
        startActivity(intent)
    }

    // 회원가입 성공 시 로그인 화면으로 자동 전환
    fun switchToSignIn() {
        supportFragmentManager.commit {
            replace(R.id.fragment_menu, SignInFragment())
        }
        // signin도 같이 눌리도록 수정
        val signin = findViewById<Button>(R.id.signinbtn)
        signin.performClick()
    }
}