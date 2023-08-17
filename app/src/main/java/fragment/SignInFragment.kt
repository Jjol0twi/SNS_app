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
import com.example.TakeMeWithYou.MainPageActivity
import com.example.TakeMeWithYou.R
import com.example.TakeMeWithYou.SignInActivity
import java.nio.BufferUnderflowException

class SignInFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.sign_in_fragment, container, false)

        // 로그인 페이지 button 선언
        val login = view.findViewById<Button>(R.id.loginBtn)
        val loginID = view.findViewById<EditText>(R.id.login_id_edittext).text.toString()
        val loginPW = view.findViewById<EditText>(R.id.login_pw_edittext).text.toString()


        login.setOnClickListener {
            // 가상 ID, PW의 edittext가 일치하면 로그인 성공 + 페이지 전환
            if(loginID == "user" && loginPW == "123") {
                (activity as? SignInActivity)?.loginSuccess()
            }
            // 아닐 경우, Toast 메세지 출력
            else
                Toast.makeText(activity, "아이디 / 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}
