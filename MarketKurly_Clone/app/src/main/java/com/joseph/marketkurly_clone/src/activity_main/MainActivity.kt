package com.joseph.marketkurly_clone.src.activity_main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.joseph.marketkurly_clone.ApplicationClass.Companion.CURRENT_USER
import com.joseph.marketkurly_clone.ApplicationClass.Companion.LOGIN_STATUS
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.Constants.REQUEST_CODE_LOGIN
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_cart.CartActivity
import com.joseph.marketkurly_clone.src.activity_main.interfaces.LoadUserInfoEvent
import com.joseph.marketkurly_clone.src.activity_main.models.UserInfo
import com.joseph.marketkurly_clone.src.db.Cart
import com.joseph.marketkurly_clone.src.db.CartEvent
import com.joseph.marketkurly_clone.src.db.CartService
import com.joseph.marketkurly_clone.src.models.Login
import com.joseph.marketkurly_clone.src.util.setGone
import com.joseph.marketkurly_clone.src.util.setInVisible
import com.joseph.marketkurly_clone.src.util.setVisible
import kotlinx.android.synthetic.main.actionbar_main_top.*
import kotlinx.android.synthetic.main.actionbar_main_top.view.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener,
    LoadUserInfoEvent, CartEvent {

    val TAG = "[ 로그 ]"

    private var mUserInforService: UserInfoService = UserInfoService(this)
    private var mCartService: CartService = CartService(this)

    private var mCartCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mUserInforService.loadUserInfo()

        initActivity()
        initActionbar()
    }

    fun initActivity() {
        main_menu_bottomnav.setupWithNavController(findNavController(R.id.nav_host_fragment_container))
        main_menu_bottomnav.setOnNavigationItemSelectedListener(this)
    }

    fun initActionbar() {
        ab_main_cart_imageview.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
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
        mCartCount += user.cartCount
        mCartService.loadCartSize()
    }

    override fun onResume() {
        super.onResume()
        mCartService.loadCartSize()
    }

    override fun onDestroy() {
        super.onDestroy()
        mCartService.onCleared()
    }

    override fun onLoadUserInfoFail(message: String) {
        CURRENT_USER = null
        LOGIN_STATUS = Login.NOT_LOGGED
    }

    fun setCart(count: Int) {
        if (count != 0) {
            main_actionbar.ab_main_cart_badge.text = count.toString()
            main_actionbar.ab_main_cart_badge.setVisible()
        } else {
            main_actionbar.ab_main_cart_badge.setInVisible()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_CODE_LOGIN -> {
                if (resultCode == RESULT_OK) {
                    showSnackBar("로그인에 성공하였습니다.")
                    mCartService.loadAllCart()
                }
            }
        }
    }

    override fun onCartAddedSuccess() {
        TODO("Not yet implemented")
    }

    override fun onCartAddedFail() {
        TODO("Not yet implemented")
    }

    override fun onCartLoadSuccess(list: List<Cart>?) {
        Log.d(TAG, "[MainActivity] - onCartLoadSuccess() : ${list!!.size}")
        if(list.isNotEmpty()){
            mCartCount = list.size
            setCart(mCartCount)
            main_actionbar.ab_main_cart_badge.text = mCartCount.toString()
        }

    }

    override fun onCartLoadFail() {
        Log.d(TAG, "[MainActivity] - onCartLoadFail() : 카트 로드 실패")

    }

    override fun onCartSizeLoadSuccess(size: Int) {
        Log.d(TAG, "[MainActivity] - onCartSizeLoadSuccess() : $size")
        mCartCount += size
        setCart(mCartCount)
        main_actionbar.ab_main_cart_badge.text = size.toString()
    }

    override fun onCartSizeLoadFail() {
        TODO("Not yet implemented")
    }

    override fun onUpdateSuccess() {
        TODO("Not yet implemented")
    }

    override fun onUpdateFail() {
        TODO("Not yet implemented")
    }

}