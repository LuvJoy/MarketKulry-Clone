package com.joseph.marketkurly_clone

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.joseph.marketkurly_clone.Constants.SHARED_PREPERENCE_KEY
import com.joseph.marketkurly_clone.Constants.TAG
import com.joseph.marketkurly_clone.src.activity_main.models.UserInfo
import com.joseph.marketkurly_clone.src.db.Cart
import com.joseph.marketkurly_clone.src.db.CartDatabase
import com.joseph.marketkurly_clone.src.models.Login
import com.joseph.marketkurly_clone.src.util.getToken

class ApplicationClass: Application() {

    companion object{
        var sSharedPreferences: SharedPreferences? = null
        var LOGIN_STATUS: Login = Login.NOT_LOGGED
        var CURRENT_USER: UserInfo? = null
        var CART_LIST: List<Cart>? = null
        var DB_CART: CartDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()

        if (sSharedPreferences == null) {
            sSharedPreferences = applicationContext.getSharedPreferences(
                SHARED_PREPERENCE_KEY,
                Context.MODE_PRIVATE
            )
            Log.d(TAG, "[ApplicationClass] - onCreate() : $sSharedPreferences")
        }
        if(sSharedPreferences?.getToken() == null) {
            LOGIN_STATUS = Login.NOT_LOGGED
        }

        if(DB_CART == null) {
            DB_CART = CartDatabase.getInstance(this.applicationContext)
        }

    }
}