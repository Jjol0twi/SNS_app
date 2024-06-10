package com.example.TakeMeWithYou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.TakeMeWithYou.data.UserList
import com.example.TakeMeWithYou.databinding.SignInActivityBinding

lateinit var loginLauncher: ActivityResultLauncher<Intent>

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: SignInActivityBinding

    private val userList = UserList.getInstance()
    var signId: String? = null
    var signPW: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignInActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        val id_data = findViewById<EditText>(R.id.idEditText)
        val pw_data = findViewById<EditText>(R.id.pwEditText)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        val btnSignup = findViewById<Button>(R.id.btn_signup)

        // 허용된 특자문자 사용 판별
        var id_check = false
        var pw_check = false

        id_data.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!s.toString().matches(Regex("^[a-zA-Z0-9]*$")))
                    id_data.error = getString(R.string.sigin_id_error)
                else {
                    id_data.error = null
                    id_check = true
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        pw_data.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!s.toString().matches(Regex("^[a-zA-Z0-9!@#\$%^&*()]*\$")))
                    pw_data.error = getString(R.string.sigin_pw_error)
                else {
                    pw_data.error = null
                    pw_check = true
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        loginLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val data = result.data
                    signId = data?.getStringExtra("id") ?: ""
                    signPW = data?.getStringExtra("pw") ?: ""
                    id_data.setText(signId)
                    pw_data.setText(signPW)
                }
            }

        btnLogin.setOnClickListener {
            val id = id_data.text.toString()
            val pw = pw_data.text.toString()

            if (id in userList.getUserIds() && pw in userList.getUserPWs() && id_check && pw_check) {
                val intent = Intent(this, MainpageActivity::class.java)
                userList.setNowUser(id)
                startActivity(intent)
            } else {
                Toast.makeText(this, getString(R.string.sigin_login_error), Toast.LENGTH_SHORT).show()
            }
        }

        btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            loginLauncher.launch(intent)
        }
    }

    private fun initView() = with(binding) {
        btnLogin.setOnClickListener {  }
        btnSignup.setOnClickListener {  }
    }
}