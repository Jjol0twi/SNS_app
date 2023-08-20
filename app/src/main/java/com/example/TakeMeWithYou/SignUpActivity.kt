package com.example.TakeMeWithYou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.TakeMeWithYou.data.UserList
import com.example.TakeMeWithYou.model.User


class SignUpActivity : AppCompatActivity() {
    private val userList = UserList.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_activity)

        val name = findViewById<EditText>(R.id.nameText)
        val id = findViewById<EditText>(R.id.idText)
        val pw = findViewById<EditText>(R.id.pwText)
        val signUpBtn = findViewById<Button>(R.id.button)

        var bool_btn = false

        // id 입력 시 userId editText 활성화
        id.addTextChangedListener(object : TextWatcher {
            // 텍스트 입력 후 호출
            override fun afterTextChanged(s: Editable?) {
                // id는 영어 및 숫자만 입력 가능
                if (!s.toString().matches(Regex("^[a-zA-Z0-9]*$"))) {
                    id.error = getString(R.string.sigin_id_error)
                    name.isEnabled = false
                } else {
                    id.error = null
                    if (s?.isNotEmpty() == true)
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
                if (!s.toString().matches(Regex("^[a-zA-Z0-9!@#$%^&*()]*$"))) {
                    name.error = getString(R.string.sigin_pw_error)
                } else {
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
                if (!s.toString().matches(Regex("^[a-zA-Z0-9!@#$%^&*()]*$"))) {
                    pw.error = getString(R.string.sigin_pw_error)
                } else {
                    pw.error = null
                    bool_btn = true
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        signUpBtn.setOnClickListener {
            val strID = id.text.toString()
            val strPW = pw.text.toString()
            val strName = name.text.toString()

            if (name.text.isNotEmpty() && id.text.isNotEmpty() && pw.text.isNotEmpty() && bool_btn) {
                val user = User(strID, strPW, strName)
                userList.addUser(user)

                val intent = Intent(this, SignInActivity::class.java)
                intent.putExtra("id", strID)
                intent.putExtra("pw", strPW)
                intent.putExtra("name", strName)

                setResult(RESULT_OK, intent)

                Toast.makeText(this, getString(R.string.signup_success), Toast.LENGTH_SHORT).show()
                finish()

            } else {
                Toast.makeText(this, getString(R.string.signup_failed), Toast.LENGTH_SHORT).show()
            }
        }
    }
}