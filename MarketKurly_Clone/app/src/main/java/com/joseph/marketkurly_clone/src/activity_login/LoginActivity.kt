package com.joseph.marketkurly_clone.src.activity_login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.R
import kotlinx.android.synthetic.main.actionbar_inner_page_top.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initActivity()
    }

    fun initActivity() {
        ab_inner_back_button.setOnClickListener(this)
        ab_inner_title_textview.text = "로그인"
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.ab_inner_back_button-> {
                onBackPressed()
            }
        }
    }
}