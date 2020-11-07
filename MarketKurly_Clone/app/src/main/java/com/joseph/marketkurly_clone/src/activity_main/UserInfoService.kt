package com.joseph.marketkurly_clone.src.activity_main

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.joseph.marketkurly_clone.NetworkConstants.KURLY_URL
import com.joseph.marketkurly_clone.RetrofitClient
import com.joseph.marketkurly_clone.src.activity_main.interfaces.LoadUserInfoEvent
import com.joseph.marketkurly_clone.src.activity_main.models.UserInfo
import com.joseph.marketkurly_clone.src.activity_main.network.UserInfoApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserInfoService(private var listener: LoadUserInfoEvent) {

    private var mRetrofit = RetrofitClient.getClient(KURLY_URL).create(UserInfoApi::class.java)

    fun loadUserInfo() {
        mRetrofit.loadUserInfo().enqueue(object: Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val body = response.body()
                val isSuccess = body?.get("is_success")?.asBoolean

                if(isSuccess!!) {
                    val userInfo = Gson().fromJson(body.get("result"), UserInfo::class.java)
                    listener.onLoadUserInfoSuccess(userInfo)
                } else {
                    val message = body.get("message").asString
                    listener.onLoadUserInfoFail(message)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                listener.onLoadUserInfoFail(t.message!!)
            }

        })
    }

}