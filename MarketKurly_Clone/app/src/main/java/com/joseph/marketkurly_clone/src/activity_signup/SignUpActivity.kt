package com.joseph.marketkurly_clone.src.activity_signup

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.util.setVisible
import kotlinx.android.synthetic.main.actionbar_inner_page_top.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : BaseActivity(), View.OnFocusChangeListener {

    val TAG = "[ 로그 ]"
    private lateinit var mSignUpValidationManager: SignUpValidationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initAcitivty()
        settingsActionBar()

        mSignUpValidationManager = SignUpValidationManager(this)

        signup_id_edittext.addTextChangedListener {
            val text = it.toString()
            Log.d(TAG, "[SignUpActivity] - TextChangedListener() : ${text}")

            mSignUpValidationManager.checkValidationID(text)

            if(mSignUpValidationManager.ID_COMBINATION_VALIDATION) {
                mSignUpValidationManager.setSuccess(signup_id_validation_combination)
            } else {
                mSignUpValidationManager.setNotSuccess(signup_id_validation_combination)
            }
        }
        signup_pw_edittext.addTextChangedListener {

        }

    }

    fun initAcitivty() {
        signup_play_signup_button.setOnClickListener(this)

        signup_id_edittext.onFocusChangeListener = this
        signup_pw_edittext.onFocusChangeListener = this
        signup_pw_check_edittext.onFocusChangeListener = this
        signup_name_edittext.onFocusChangeListener = this
        signup_email_edittext.onFocusChangeListener = this
        signup_phone_num_edittext.onFocusChangeListener = this
        signup_adress_edittext.onFocusChangeListener = this
        signup_birth_year_edittext.onFocusChangeListener = this
        signup_birth_month_edittext.onFocusChangeListener = this
        signup_birth_day_edittext.onFocusChangeListener = this
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

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (hasFocus) {
            when (v?.id) {
                R.id.signup_id_edittext -> {
                    Log.d(TAG, "[SignUpActivity] - onFocusChange() : signup_id_edittext")
                    signup_id_validation_layout.setVisible()
                }

                R.id.signup_pw_edittext -> {
                    Log.d(TAG, "[SignUpActivity] - onFocusChange() : signup_pw_edittext")
                    signup_pw_validation_layout.setVisible()
                }

                R.id.signup_pw_check_edittext -> {
                    Log.d(TAG, "[SignUpActivity] - onFocusChange() : signup_pw_check_edittext")
                    signup_pw_check_validation_layout.setVisible()
                }

                R.id.signup_name_edittext -> {
                    Log.d(TAG, "[SignUpActivity] - onFocusChange() : signup_name_edittext")
                }

                R.id.signup_email_edittext -> {
                    Log.d(TAG, "[SignUpActivity] - onFocusChange() : signup_email_edittext")
                }

                R.id.signup_phone_num_edittext -> {
                    Log.d(TAG, "[SignUpActivity] - onFocusChange() : signup_phone_num_edittext")
                }

                R.id.signup_adress_edittext -> {
                    Log.d(TAG, "[SignUpActivity] - onFocusChange() : signup_adress_edittext")
                }

                R.id.signup_birth_year_edittext -> {
                    Log.d(TAG, "[SignUpActivity] - onFocusChange() : signup_birth_year_edittext")
                }

                R.id.signup_birth_month_edittext -> {
                    Log.d(TAG, "[SignUpActivity] - onFocusChange() : signup_birth_month_edittext")
                }

                R.id.signup_birth_day_edittext -> {
                    Log.d(TAG, "[SignUpActivity] - onFocusChange() : signup_birth_day_edittext")
                }

            }
        }
    }
}