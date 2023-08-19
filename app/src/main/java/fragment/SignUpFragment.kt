package fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.TakeMeWithYou.R
import com.example.TakeMeWithYou.SignInActivity
import com.example.TakeMeWithYou.User

class SignUpFragment : Fragment() {
    private val userMap : MutableMap<String, User> = mutableMapOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater.from(activity).inflate(R.layout.sign_up_fragment, container, false)

        var bool_btn = false

        // signUp의 editText, button 상수 선언
        val signUp = view.findViewById<Button>(R.id.signUpBtn)
        val id_edit = view.findViewById<EditText>(R.id.signup_id_editText)
        val pw_edit = view.findViewById<EditText>(R.id.signup_pw_editText)
        val userId_edit = view.findViewById<EditText>(R.id.signup_userId_editText)

        // id 입력 전 pw, userId editText 입력 불가
        pw_edit.isEnabled = false
        userId_edit.isEnabled = false

        // id 입력 시 userId editText 활성화
        id_edit.addTextChangedListener(object: TextWatcher {
            // 텍스트 입력 후 호출
            override fun afterTextChanged(s: Editable?) {
                // id는 영어 및 숫자만 입력 가능
                if(!s.toString().matches(Regex("^[a-zA-Z0-9]*$"))) {
                    id_edit.error = "영어 / 숫자만 입력이 가능"
                    userId_edit.isEnabled = false
                }
                else {
                    id_edit.error = null
                    if(s?.isNotEmpty() == true)
                        userId_edit.isEnabled = true
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
        userId_edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // userId는 영어, 숫자, 특수문자 입력 가능
                if(!s.toString().matches(Regex("^[a-zA-Z0-9!@#$%^&*()]*$"))) {
                    userId_edit.error = "영어 및 특정 특수문자만 사용 가능"
                }
                else {
                    userId_edit.error = null
                    pw_edit.isEnabled = true
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        // pw는 영어, 숫자 및 특수문자만 입력 가능
        pw_edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(!s.toString().matches(Regex("^[a-zA-Z0-9!@#$%^&*()]*$"))) {
                    pw_edit.error = "영어 및 특정 특수문자만 사용 가능"
                }
                else {
                    pw_edit.error = null
                    bool_btn = true
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        signUp.setOnClickListener {
            val id = id_edit.text.toString()
            val pw = pw_edit.text.toString()
            val userId = userId_edit.text.toString()

            // id, pw, userId가 제대로 입력이 된 경우
            if(id.isNotEmpty() && pw.isNotEmpty() && userId.isNotEmpty() && bool_btn) {
                val intent = Intent().apply {
                    putExtra("id", id)
                    putExtra("pw", pw)
                    putExtra("name", userId)
                }

                parentFragmentManager.setFragmentResult("singUp", bundleOf("signUp_data" to intent))

                // 회원가입 성공 시 로그인 화면으로 자동 이동
                Toast.makeText(activity, "회원가입 성공 !", Toast.LENGTH_SHORT).show()
                (activity as SignInActivity).switchToSignIn()
            }
            else
                Toast.makeText(activity, "정보를 제대로 입력해주세요.", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}