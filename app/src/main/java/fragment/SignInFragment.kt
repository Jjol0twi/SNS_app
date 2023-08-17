package fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.TakeMeWithYou.R

class SignInFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.sign_in_fragment, container, false)

        // 로그인 페이지 button, edittext 상수 선언
        val login = view.findViewById<Button>(R.id.loginBtn)
        val loginID = view.findViewById<EditText>(R.id.login_id_edittext)
        val loginPW = view.findViewById<EditText>(R.id.login_pw_edittext)

        return view
    }
}
