package com.example.myapplication.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.Model.ViewPagerAdapter
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_main_two.*

class MainActivityTwo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_two)

        view_pager.adapter = ViewPagerAdapter(supportFragmentManager)
        tab_layout.setupWithViewPager(view_pager)
    }
}