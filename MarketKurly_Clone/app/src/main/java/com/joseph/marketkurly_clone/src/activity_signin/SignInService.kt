package com.joseph.marketkurly_clone.src.activity_signin

import android.util.Log
import com.google.gson.JsonObject
import com.joseph.marketkurly_clone.Constants
import com.joseph.marketkurly_clone.NetworkConstants
import com.joseph.marketkurly_clone.RetrofitClient
import com.joseph.marketkurly_clone.src.activity_signin.interfaces.SignInApiEvent
import com.joseph.marketkurly_clone.src.activity_signin.network.SignInApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInService(var listener: SignInApiEvent) {

    private var client = RetrofitClient.getClient(NetworkConstants.KURLY_URL).create(SignInApi::class.java)

    fun signIn(userAccount: JsonObject) {
        client.signIn(userAccount).enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                val resultCode: Int = response.body()?.get("code")?.asInt!!
                var jwt: String? = null

                when (resultCode) {
                    200 -> {
                        jwt = response.body()?.get("result")?.asJsonObject?.get("jwt")?.asString
                        listener.onSignInSuccess(jwt)
                    }

                    310 -> {
                        listener.onSignInFail("사용자정보가 일치하지 않습니다.")
                    }
                }

                Log.d(Constants.TAG, "[LoginActivity] - onResponse() : $jwt ")
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.d(Constants.TAG, "[LoginActivity] - onFailure() : ${t.message} ")
            }

        })
    }


}