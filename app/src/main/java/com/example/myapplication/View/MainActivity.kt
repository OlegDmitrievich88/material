package com.example.myapplication.View

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.View.FragmentStart.Companion.newInstance


private const val THEME_NAME = "theme_name"
private const val THEME = "theme"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val settingTheme: SharedPreferences =getSharedPreferences(THEME, Context.MODE_PRIVATE)

        when(settingTheme.getString(THEME_NAME,null)){
            "one" -> setTheme(R.style.Theme_MyAppCustom)
            "two" -> setTheme(R.style.Theme_MyApplication)
            "three" -> setTheme(R.style.Theme_MyAppCustomTwo)
        }



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container,newInstance())
                .addToBackStack(null)
                .commit()
        }
    }

}