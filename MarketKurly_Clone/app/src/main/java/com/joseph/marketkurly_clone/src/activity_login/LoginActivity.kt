package com.joseph.marketkurly_clone.src.activity_login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.joseph.marketkurly_clone.ApplicationClass.Companion.sSharedPreferences
import com.joseph.marketkurly_clone.BaseActivity
import com.joseph.marketkurly_clone.Constants
import com.joseph.marketkurly_clone.NetworkConstants.KURLY_URL
import com.joseph.marketkurly_clone.RetrofitClient
import com.joseph.marketkurly_clone.R
import com.joseph.marketkurly_clone.src.activity_login.network.AccountService
import com.joseph.marketkurly_clone.src.activity_signup.SignUpActivity
import com.joseph.marketkurly_clone.src.util.setToken
import kotlinx.android.synthetic.main.actionbar_inner_page_top.*
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : BaseActivity() {

    private var client = RetrofitClient.getClient(KURLY_URL).create(AccountService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initActivity()
        settingsActionBar()

    }

    fun initActivity() {
        login_signin_button.setOnClickListener(this)
        login_signup_button.setOnClickListener(this)
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

                client.signIn(user)?.enqueue(object : Callback<JsonObject> {
                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                        val body = response.body()
                        val jwt = body?.get("jwt")?.asString

                        sSharedPreferences?.setToken(jwt!!)
                        Log.d(Constants.TAG, "[LoginActivity] - onResponse() : $jwt ")
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        Log.d(Constants.TAG, "[LoginActivity] - onFailure() : ${t.message} ")
                    }

                })
            }
        }
    }
}
