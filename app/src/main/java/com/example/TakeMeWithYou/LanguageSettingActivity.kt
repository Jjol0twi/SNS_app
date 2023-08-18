package com.example.TakeMeWithYou

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale


class LanguageSettingActivity : AppCompatActivity() {
    private lateinit var korSettingLanguageBtn: RadioButton
    private lateinit var engSettingLanguageBtn: RadioButton
    private lateinit var languageCode: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.language_setting_button)

        korSettingLanguageBtn = findViewById(R.id.setting_language_kor_btn)
        engSettingLanguageBtn = findViewById(R.id.setting_language_en_btn)

        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", "")
        if (language != null) {
            languageCode = language
        }
        if (languageCode.equals("en") || languageCode.equals("")) {
            engSettingLanguageBtn.isChecked
        } else {
            korSettingLanguageBtn.isChecked
        }
        korSettingLanguageBtn.setOnClickListener {
            setLocate("ko")
            recreate()
        }
        engSettingLanguageBtn.setOnClickListener {
            setLocate("en")
            recreate()
        }

    }

    private fun setLocate(Lang: String) {
        val locale = Locale(Lang)

        Locale.setDefault(locale)

        val config = Configuration()

        config.setLocale(locale)

        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }
}
