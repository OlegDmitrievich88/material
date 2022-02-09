package com.example.myapplication.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.View.FragmentStart.Companion.newInstance

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
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