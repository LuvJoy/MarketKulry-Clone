package com.joseph.marketkurly_clone.src.activity_signup

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.google.gson.JsonObject
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.NetworkConstants.KURLY_URL
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.RetrofitClient
import com.joseph.marketkurly_clone.src.activity_signup.network.SignUpAPI
import com.joseph.marketkurly_clone.src.util.setVisible
import kotlinx.android.synthetic.main.actionbar_inner_page_top.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BaseActivity(), View.OnFocusChangeListener {

    val TAG = "[ 로그 ]"
    private lateinit var mSignUpValidationManager: SignUpValidationManager
    private var mRetrofitClient = RetrofitClient.getClient(KURLY_URL).create(SignUpAPI::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initAcitivty()
        initManager()
        settingsActionBar()
        settingsEditTextListener()

    }

    fun initAcitivty() {
        signup_play_signup_button.setOnClickListener(this)
        signup_id_check_button.setOnClickListener(this)

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

    fun initManager() {
        mSignUpValidationManager = SignUpValidationManager(this)
    }

    fun settingsActionBar() {
        ab_inner_toolbar.title = "회원가입"
        ab_inner_toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun settingsEditTextListener() {
        signup_id_edittext.addTextChangedListener {
            val text = it.toString()
            Log.d(TAG, "[SignUpActivity] - TextChangedListener() : ${text}")

            mSignUpValidationManager.checkIdCombination(text, signup_id_validation_combination)
        }

        signup_pw_edittext.addTextChangedListener {
            val text = it.toString()

            mSignUpValidationManager.checkPwLength(text, signup_pw_validation_length)
            mSignUpValidationManager.checkPwCombination(text, signup_pw_validation_combination)
            mSignUpValidationManager.checkPwSameNumber(text, signup_pw_validation_same_number)
        }

        signup_pw_check_edittext.addTextChangedListener {
            val text = it.toString()
            val password = signup_pw_edittext.text.toString()
            mSignUpValidationManager.checkPwSame(text, password, signup_pw_check_validation_same)
        }
    }


    override fun onClick(v: View?) {
        when (v?.id) {

            // 회원가입하는 버튼
            R.id.signup_play_signup_button -> {

            }

            // ID 중복체크 버튼
            R.id.signup_id_check_button -> {
                checkDuplicateID()
            }

        }
    }

    // EditText가 클릭됐는지 안됐는지를 감지한다.
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

    // 아이디 중복체크
    fun checkDuplicateID() {
        val id = signup_id_edittext.text.toString()
        val json: JsonObject = JsonObject()
        json.addProperty("id", id)
        mRetrofitClient.checkDuplicateID(json).enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val isExist = response.body()?.get("is_exist")?.asString!!
                if (isExist == "Y") {
                    showCustomToast("아이디가 이미 존재합니다.")
                    mSignUpValidationManager.mValidationHash["ID_DUPLICATE"] = false
                    mSignUpValidationManager.setTextViewNotSuccess(signup_id_validation_duplicate)
                } else {
                    showCustomToast("사용가능한 아이디입니다.")
                    mSignUpValidationManager.mValidationHash["ID_DUPLICATE"] = true
                    mSignUpValidationManager.setTextViewSuccess(signup_id_validation_duplicate)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


}