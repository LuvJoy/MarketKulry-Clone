package com.joseph.marketkurly_clone.src.activity_signin

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.gson.JsonObject
import com.joseph.marketkurly_clone.ApplicationClass.Companion.CURRENT_USER
import com.joseph.marketkurly_clone.ApplicationClass.Companion.LOGIN_STATUS
import com.joseph.marketkurly_clone.ApplicationClass.Companion.sSharedPreferences
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_main.models.UserInfo
import com.joseph.marketkurly_clone.src.activity_signin.interfaces.SignInApiEvent
import com.joseph.marketkurly_clone.src.activity_signup.SignUpActivity
import com.joseph.marketkurly_clone.src.models.Login
import com.joseph.marketkurly_clone.src.util.setToken
import kotlinx.android.synthetic.main.actionbar_inner_page_top.*
import kotlinx.android.synthetic.main.activity_login.*

class SignInActivity : BaseActivity(), SignInApiEvent {

    private lateinit var mSignInService: SignInService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initActivity()
        initObject()
        settingsActionBar()

    }

    fun initActivity() {
        login_signin_button.setOnClickListener(this)
        login_signup_button.setOnClickListener(this)
    }

    fun initObject() {
        mSignInService = SignInService(this)
    }

    fun settingsActionBar() {
        ab_inner_toolbar.title = "로그인"
        ab_inner_toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.login_signup_button -> {

                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)

            }
            R.id.login_signin_button -> {
                val user = JsonObject()
                user.apply {
                    addProperty("id", login_id_edittext.text.toString())
                    addProperty("password", login_pw_edittext.text.toString())
                }
                mSignInService.signIn(user)
                showProgressDialog()
            }
        }
    }

    override fun onSignInSuccess(token: String, userInfo: UserInfo) {
        sSharedPreferences?.setToken(token)
        LOGIN_STATUS = Login.LOGGED
        CURRENT_USER = userInfo
        hideProgressDialog()
        onBackPressed()
    }

    override fun onSignInFail(message: String) {
        showAlertDialog(message)
        hideProgressDialog()
    }

    override fun onDestroy() {
        // 코루틴 Job Cancle
        mSignInService.onCleared()
        super.onDestroy()
    }
}
