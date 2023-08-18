package fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
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