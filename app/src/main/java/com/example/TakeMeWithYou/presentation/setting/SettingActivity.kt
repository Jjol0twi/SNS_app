package com.example.TakeMeWithYou.presentation.setting

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.TakeMeWithYou.R
import com.example.TakeMeWithYou.presentation.signin.SignInActivity
import com.example.TakeMeWithYou.data.UserList
import java.util.Locale

class SettingActivity : AppCompatActivity() {

    private val settingToolbar: Toolbar by lazy { findViewById(R.id.setting_toolbar) }
    private lateinit var settingMainListView: LinearLayout
    private val settingMainItems: Array<Int> =
        arrayOf(
            R.string.setting_item_langauge,
            R.string.setting_item_font,
            R.string.setting_item_theme,
            R.string.setting_item_logout
        )
    private val settingSubItems: Map<Int, Array<Int>> = mapOf(
        R.string.setting_item_langauge to arrayOf(
            R.string.setting_item_langauge_system,
            R.string.setting_item_langauge_ko,
            R.string.setting_item_langauge_en
        ),
        R.string.setting_item_font to arrayOf(R.string.setting_item_font_item),
        R.string.setting_item_theme to arrayOf(R.string.setting_item_theme_item),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting_activity)
        settingMainListView = findViewById(R.id.setting_main_listview)
        setSupportActionBar(settingToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        initListView()
        settingToolbar.setOnMenuItemClickListener { it ->
            when (it.itemId) {
                R.id.setting_complete -> {
                    finish()
                    true

                }

                else -> {
                    false
                }
            }

        }
    }

    private fun initListView() {
        for (i in settingMainItems) {
            val settingView = layoutInflater.inflate(R.layout.setting_listview_item, null)
            val settingText: TextView = settingView.findViewById(R.id.setting_listview_item_text)
            settingText.text = getString(i)
            settingMainListView.addView(settingView)
            val settingSubContainer: LinearLayout =
                layoutInflater.inflate(
                    R.layout.setting_listview_container_subitem,
                    null
                ) as LinearLayout
            settingMainListView.addView(settingSubContainer)
            settingView.setOnClickListener {
                if (settingSubItems[i] != null) {
                    if (settingSubContainer.visibility == View.VISIBLE) {
                        settingSubContainer.visibility = View.GONE
                    } else {
                        settingSubContainer.visibility = View.VISIBLE
                        settingSubContainer.removeAllViews()
                        for (j in settingSubItems[i]!!) {
                            val settingChildView =
                                layoutInflater.inflate(R.layout.setting_listview_subitem, null)
                            val settingSubText: TextView =
                                settingChildView.findViewById(R.id.setting_listview_subItem_text)
                            settingSubText.text = getString(j)
                            settingSubContainer.addView(settingChildView)
                            settingChildView.setOnClickListener {
                                clickSettingItem(j)
                            }
                        }
                    }

                } else {
                    clickSettingItem(i)
                }
            }
        }
    }

    private fun setLocate(Lang: String) {
        val locale = Locale(Lang)

        Locale.setDefault(locale)

        val config = Configuration()
        config.setLocale(locale)

        val resources = baseContext.resources
        val context = resources.configuration
        context.locale = locale

        resources.updateConfiguration(config, resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }

    private fun clickSettingItem(j: Int) {
        if (j == R.string.setting_item_langauge_system) {
            val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
            val language = sharedPreferences.getString("My_Lang", "ko")
            if (language != null) {
                setLocate(language)
                recreate()
            }
        }
        if (j == R.string.setting_item_langauge_ko) {
            setLocate("ko")
            recreate()
        }
        if (j == R.string.setting_item_langauge_en) {
            setLocate("en")
            recreate()
        }
        if (j == R.string.setting_item_logout) {
            UserList.getInstance().setNowUser("")
            finish()
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.setting_toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}