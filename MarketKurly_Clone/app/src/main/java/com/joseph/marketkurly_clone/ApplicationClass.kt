package com.joseph.marketkurly_clone

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.joseph.marketkurly_clone.ApplicationClass.Companion.sSharedPreferences
import com.joseph.marketkurly_clone.Constants.SHARED_PREPERENCE_KEY
import com.joseph.marketkurly_clone.Constants.TAG
import com.joseph.marketkurly_clone.src.models.Login
import com.joseph.marketkurly_clone.src.util.getToken

class ApplicationClass: Application() {

    companion object{
        var sSharedPreferences: SharedPreferences? = null
        var LOGIN_STATUS = Login.NOT_LOGGED
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

    }
}