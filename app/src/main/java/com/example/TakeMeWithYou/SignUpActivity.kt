package com.example.TakeMeWithYou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_activity)

        val name = findViewById<EditText>(R.id.nameText)
        val id = findViewById<EditText>(R.id.idText)
        val pw = findViewById<EditText>(R.id.pwText)
        val signupbtn = findViewById<Button>(R.id.button)

        var bool_btn = false

        // id 입력 시 userId editText 활성화
        id.addTextChangedListener(object: TextWatcher {
            // 텍스트 입력 후 호출
            override fun afterTextChanged(s: Editable?) {
                // id는 영어 및 숫자만 입력 가능
                if(!s.toString().matches(Regex("^[a-zA-Z0-9]*$"))) {
                    id.error = "영어 / 숫자만 입력이 가능"
                    name.isEnabled = false
                }
                else {
                    id.error = null
                    if(s?.isNotEmpty() == true)
                        name.isEnabled = true
                }
            }
            // 텍스트 변경 전 호출
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            // 텍스트 변경 중 호출
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        // id -> userId 입력시 pw editText 활성화
        name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // userId는 영어, 숫자, 특수문자 입력 가능
                if(!s.toString().matches(Regex("^[a-zA-Z0-9!@#$%^&*()]*$"))) {
                    name.error = "영어 및 특정 특수문자만 사용 가능"
                }
                else {
                    name.error = null
                    pw.isEnabled = true
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        // pw는 영어, 숫자 및 특수문자만 입력 가능
        pw.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(!s.toString().matches(Regex("^[a-zA-Z0-9!@#$%^&*()]*$"))) {
                    pw.error = "영어 및 특정 특수문자만 사용 가능"
                }
                else {
                    pw.error = null
                    bool_btn = true
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        signupbtn.setOnClickListener {
            var strid = id.text.toString()
            var strpw = pw.text.toString()
            var strname = name.text.toString()

            if( name.text.isNotEmpty() && id.text.isNotEmpty() && pw.text.isNotEmpty() && bool_btn) {
                val intent = Intent(this, SignInActivity2::class.java)
                intent.putExtra("id", strid)
                intent.putExtra("pw", strpw)
                intent.putExtra("name", strname)


                setResult(RESULT_OK, intent)

                Toast.makeText(this, "회원가입 성공 !", Toast.LENGTH_SHORT).show()
                finish()

            }
            else
            {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다 !", Toast.LENGTH_SHORT).show()
            }
        }
    }
}