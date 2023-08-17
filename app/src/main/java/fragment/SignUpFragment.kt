package fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
        val id = view.findViewById<EditText>(R.id.signup_id_editText).text.toString()
        val pw = view.findViewById<EditText>(R.id.signup_pw_editText).text.toString()
        val userId = view.findViewById<EditText>(R.id.signup_userId_editText).text.toString()

        signUp.setOnClickListener {
            Toast.makeText(activity, "success!", Toast.LENGTH_SHORT).show()
            // 회원가입 성공 시 로그인 화면으로 자동 이동
            (activity as SignInActivity)?.switchToSignIn()
        }

        return view
    }
}