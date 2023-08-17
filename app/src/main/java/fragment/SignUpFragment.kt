package fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.TakeMeWithYou.R

class SignUpFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.sign_up_fragment, container, false)

        // signUp의 editText, button 상수 선언
        val signUp = view.findViewById<Button>(R.id.signUpBtn)
        val id = view.findViewById<EditText>(R.id.signup_id_editText)
        val pw = view.findViewById<EditText>(R.id.signup_pw_editText)
        val userId = view.findViewById<EditText>(R.id.signup_userId_editText)

        return view
    }
}