package com.joseph.marketkurly_clone.src.activity_splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var moveNextAcitivity: Runnable? = null
        var changeBackground: Runnable? = null
        var handle: Handler? = null

        moveNextAcitivity = Runnable {
            startActivity(Intent(this, MainActivity::class.java))
        }

        changeBackground = Runnable {
            splash_logo_imageview.visibility = View.INVISIBLE
            splash_background_imageview.visibility = View.VISIBLE
        }

        handle = Handler()
        handle. run {
            postDelayed(changeBackground!!, 2000)
            postDelayed(moveNextAcitivity!!, 4000)
        }

    }

}