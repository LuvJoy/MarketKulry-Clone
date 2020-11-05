package com.joseph.marketkurly_clone.src.activity_signin

import android.util.Log
import com.google.gson.JsonObject
import com.joseph.marketkurly_clone.ApplicationClass
import com.joseph.marketkurly_clone.Constants
import com.joseph.marketkurly_clone.NetworkConstants
import com.joseph.marketkurly_clone.RetrofitClient
import com.joseph.marketkurly_clone.src.activity_signin.network.AccountService
import com.joseph.marketkurly_clone.src.util.setToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInService {

    private var client = RetrofitClient.getClient(NetworkConstants.KURLY_URL).create(AccountService::class.java)

    fun signIn(userAccount: JsonObject) {
        client.signIn(userAccount)?.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                val body = response.body()
                val jwt = body?.get("jwt")?.asString

                Log.d(Constants.TAG, "[LoginActivity] - onResponse() : $jwt ")
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.d(Constants.TAG, "[LoginActivity] - onFailure() : ${t.message} ")
            }

        })
    }


}