package com.joseph.marketkurly_clone.src.activity_main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.joseph.marketkurly_clone.R
import kotlinx.android.synthetic.main.actionbar_main_top.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initActivity()
    }

    fun initActivity () {
        main_menu_bottomnav.setupWithNavController(findNavController(R.id.nav_host_fragment_container))
        main_menu_bottomnav.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_container)
        when(item.title) {
            "홈" -> {
                navController.navigate(R.id.homeFragment)
                ab_main_logo_imageview.visibility = View.VISIBLE
                ab_main_pagename_textview.visibility = View.INVISIBLE
            }
            "카테고리" ->{
                navController.navigate(R.id.categoryFragment)
                ab_main_logo_imageview.visibility = View.INVISIBLE
                ab_main_pagename_textview.text = "카테고리"
                ab_main_pagename_textview.visibility = View.VISIBLE
            }
            "추천" -> {
                navController.navigate(R.id.recommendFragment)
                ab_main_logo_imageview.visibility = View.INVISIBLE
                ab_main_pagename_textview.text = "추천"
                ab_main_pagename_textview.visibility = View.VISIBLE
            }
            "검색" -> {
                navController.navigate(R.id.searchFragment)
                ab_main_logo_imageview.visibility = View.INVISIBLE
                ab_main_pagename_textview.text = "검색"
                ab_main_pagename_textview.visibility = View.VISIBLE
            }
            "마이컬리" -> {
                navController.navigate(R.id.myKurlyFragment)
                ab_main_logo_imageview.visibility = View.INVISIBLE
                ab_main_pagename_textview.text = "마이컬리"
                ab_main_pagename_textview.visibility = View.VISIBLE
            }
        }

        return true
    }
}