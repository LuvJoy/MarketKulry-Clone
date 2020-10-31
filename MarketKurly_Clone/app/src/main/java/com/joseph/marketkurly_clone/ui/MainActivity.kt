package com.joseph.marketkurly_clone.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.ui.interfaces.MainActivityValidation

class MainActivity : AppCompatActivity(), MainActivityValidation {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun validateSuccess(text: String?) {

        // 인증 성공 시
        TODO("Not yet implemented")
    }

    override fun validateFailure(message: String?) {

        // 인증 실패 시
        TODO("Not yet implemented")
    }
}