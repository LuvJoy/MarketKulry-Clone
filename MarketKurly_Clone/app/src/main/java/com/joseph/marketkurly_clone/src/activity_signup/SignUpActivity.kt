package com.joseph.marketkurly_clone.src.activity_signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.R
import kotlinx.android.synthetic.main.actionbar_inner_page_top.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        settingsActionBar()
    }

    fun initAcitivty() {
        signup_play_signup_button.setOnClickListener(this)

        /*
        signup_id_edittext
        signup_pw_edittext
        signup_pw_check_edittext

        signup_name_edittext
        signup_email_edittext

        signup_phone_num_edittext

        signup_adress_edittext

        signup_birth_year_edittext
        signup_birth_month_edittext
        signup_birth_day_edittext

        signup_sex_radiogroup
        signup_additional_radiogroup
        */

    }

    fun settingsActionBar() {
        ab_inner_toolbar.title = "회원가입"
        ab_inner_toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onClick(view: View?) {
        when (view?.id) {

            R.id.signup_play_signup_button -> {

            }

        }
    }
}