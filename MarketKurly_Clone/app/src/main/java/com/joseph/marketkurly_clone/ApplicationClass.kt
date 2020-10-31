package com.joseph.marketkurly_clone

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.joseph.marketkurly_clone.constants.AppConstants.SHARED_PREPERENCE_KEY

class ApplicationClass: Application() {

    companion object{
        var sSharedPreferences: SharedPreferences? = null
    }

    override fun onCreate() {
        super.onCreate()

        if (sSharedPreferences == null) {
            sSharedPreferences = applicationContext.getSharedPreferences(
                SHARED_PREPERENCE_KEY,
                Context.MODE_PRIVATE
            )
        }
    }
}