package com.joseph.marketkurly_clone.src.activity_main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.joseph.marketkurly_clone.ApplicationClass.Companion.CURRENT_USER
import com.joseph.marketkurly_clone.ApplicationClass.Companion.LOGIN_STATUS
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_main.interfaces.LoadUserInfoEvent
import com.joseph.marketkurly_clone.src.activity_main.models.UserInfo
import com.joseph.marketkurly_clone.src.models.Login
import com.joseph.marketkurly_clone.src.util.setVisible
import kotlinx.android.synthetic.main.actionbar_main_top.*
import kotlinx.android.synthetic.main.actionbar_main_top.view.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),
        BottomNavigationView.OnNavigationItemSelectedListener,
        LoadUserInfoEvent {

    private var mUserInforService: UserInfoService = UserInfoService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mUserInforService.loadUserInfo()
        initActivity()
    }

    fun initActivity() {
        main_menu_bottomnav.setupWithNavController(findNavController(R.id.nav_host_fragment_container))
        main_menu_bottomnav.setOnNavigationItemSelectedListener(this)
    }

    fun initActionbar() {
        ab_main_cart_imageview.setOnClickListener{ showAlertDialog("굿")}
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_container)
        when (item.title) {
            "홈" -> {
                navController.navigate(R.id.homeFragment)
                ab_main_logo_imageview.visibility = View.VISIBLE
                ab_main_pagename_textview.visibility = View.INVISIBLE
            }
            "카테고리" -> {
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

    override fun onLoadUserInfoSuccess(user: UserInfo) {
        CURRENT_USER = user
        LOGIN_STATUS = Login.LOGGED
        main_actionbar.ab_main_cart_badge.setVisible()
        main_actionbar.ab_main_cart_badge.text = user.cartCount.toString()
    }

    override fun onLoadUserInfoFail(message: String) {
        CURRENT_USER = null
        LOGIN_STATUS = Login.NOT_LOGGED
    }

}