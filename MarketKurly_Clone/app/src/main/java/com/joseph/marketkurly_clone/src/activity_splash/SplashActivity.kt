package com.joseph.marketkurly_clone.src.activity_splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import com.joseph.marketkurly_clone.ApplicationClass.Companion.sSharedPreferences
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_main.MainActivity
import com.joseph.marketkurly_clone.src.util.getToken
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    val TAG = "[ 로그 ]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var moveNextAcitivity: Runnable? = null
        var changeBackground: Runnable? = null
        var handle: Handler? = null

        Log.d(TAG, "[SplashActivity] - onCreate() : ${sSharedPreferences?.getToken()}")

        moveNextAcitivity = Runnable {
            startActivity(Intent(this, MainActivity::class.java))
        }

        changeBackground = Runnable {
            val anim = AnimationUtils.loadAnimation(this, R.anim.fade_in_fast)
            anim.duration = 150

            splash_logo_imageview.visibility = View.INVISIBLE
            splash_background_imageview.visibility = View.VISIBLE
            splash_background_imageview.animation = anim
        }

        handle = Handler()
        handle. run {
            postDelayed(changeBackground, 2000)
            postDelayed(moveNextAcitivity, 4000)
        }

    }

}