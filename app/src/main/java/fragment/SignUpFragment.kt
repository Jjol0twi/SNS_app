package fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.TakeMeWithYou.R
import com.example.TakeMeWithYou.SignInActivity

class SignUpFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.sign_up_fragment, container, false)

        // signUp의 editText, button 상수 선언
        val signUp = view.findViewById<Button>(R.id.signUpBtn)
        val id_edit = view.findViewById<EditText>(R.id.signup_id_editText)
        val pw_edit = view.findViewById<EditText>(R.id.signup_pw_editText)
        val userId_edit = view.findViewById<EditText>(R.id.signup_userId_editText)

        // id는 영어 및 숫자만 입력 가능
        id_edit.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        // pw는 영어, 숫자 및 특수문자만 입력 가능
        pw_edit.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        // userId는 한/영, 숫자, 특수문자 모두 입력 가능 -> inputType 설정 필요 X

        // id 입력 전 pw, userId editText 입력 불가
        pw_edit.isEnabled = false
        userId_edit.isEnabled = false

        // id 입력 시 userId editText 활성화
        id_edit.addTextChangedListener(object: TextWatcher {
            // 텍스트 입력 후 호출
            override fun afterTextChanged(s: Editable?) {
                if(s?.isNotEmpty() == true)
                    userId_edit.isEnabled = true
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
                if(s?.isNotEmpty() == true)
                    pw_edit.isEnabled = true
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

            Toast.makeText(activity, "success!", Toast.LENGTH_SHORT).show()
            // 회원가입 성공 시 로그인 화면으로 자동 이동
            (activity as SignInActivity)?.switchToSignIn()
        }

        return view
    }
}