package fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.TakeMeWithYou.R
import com.example.TakeMeWithYou.SignInActivity
import com.example.TakeMeWithYou.User

class SignInFragment : Fragment() {
    private val userMap : MutableMap<String, User> = mutableMapOf()
    var strID : String ?= null
    var strPW : String ?= null
    var strName : String ?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater.from(activity).inflate(R.layout.sign_in_fragment, container, false)

        // 로그인 페이지 button 선언
        val login = view.findViewById<Button>(R.id.loginBtn)
        val id_edit = view.findViewById<EditText>(R.id.login_id_edittext)
        val pw_edit = view.findViewById<EditText>(R.id.login_pw_edittext)

        // 허용된 특자문자 사용 판별
        var id_check = false
        var pw_check = false

        id_edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(!s.toString().matches(Regex("^[a-zA-Z0-9]*$")))
                    id_edit.error = "영어 및 숫자만 사용 가능"
                else {
                    id_edit.error = null
                    id_check = true
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        pw_edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(!s.toString().matches(Regex("^[a-zA-Z0-9!@#\$%^&*()]*\$")))
                    pw_edit.error = "영어, 숫자 및 특정 특수문자만 사용 가능"
                else{
                    pw_edit.error = null
                    pw_check = true
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        parentFragmentManager.setFragmentResultListener("signUp", viewLifecycleOwner) { _, result ->
            val intent = result.getParcelable<Intent>("signUp_data")
            strID = intent?.getStringExtra("id")
            strPW = intent?.getStringExtra("pw")
            strName = intent?.getStringExtra("name")
        }

        login.setOnClickListener {
            val loginID = id_edit.text.toString()
            val loginPW = pw_edit.text.toString()

            // 가상 ID, PW의 edittext가 일치하면 로그인 성공 + 페이지 전환
            if(loginID == strID && loginPW == strPW && id_check && pw_check) {
                (activity as? SignInActivity)?.loginSuccess()
            }
            // 아닐 경우, Toast 메세지 출력
            else
                Toast.makeText(activity, "아이디 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}
