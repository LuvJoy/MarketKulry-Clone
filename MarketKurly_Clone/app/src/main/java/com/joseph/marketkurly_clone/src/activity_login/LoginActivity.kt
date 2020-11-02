package com.joseph.marketkurly_clone.src.activity_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_signup.SignUpActivity
import kotlinx.android.synthetic.main.actionbar_inner_page_top.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initActivity()
    }

    fun initActivity() {
        login_signup_button.setOnClickListener(this)
        ab_inner_back_button.setOnClickListener(this)
        ab_inner_title_textview.text = "로그인"
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.ab_inner_back_button-> {
                onBackPressed()
            }

            R.id.login_signup_button -> {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
        }
    }
}