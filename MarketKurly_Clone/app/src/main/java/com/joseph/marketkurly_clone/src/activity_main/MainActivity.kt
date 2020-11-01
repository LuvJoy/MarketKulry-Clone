package com.joseph.marketkurly_clone.src.activity_main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_main.interfaces.MainActivityValidation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initActivity()
    }

    fun initActivity () {
        bottomNavigationView.setupWithNavController(findNavController(R.id.nav_host_fragment_container))
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_container)
        when(item.title) {
            "홈" -> {
                navController.navigate(R.id.homeFragment)
            }
            "카테고리" ->{
                navController.navigate(R.id.categoryFragment)
            }
            "추천" -> {
                navController.navigate(R.id.recommendFragment)
            }
            "검색" -> {
                navController.navigate(R.id.searchFragment)
            }
            "마이컬리" -> {
                navController.navigate(R.id.myKurlyFragment)
            }
        }

        return true
    }
}