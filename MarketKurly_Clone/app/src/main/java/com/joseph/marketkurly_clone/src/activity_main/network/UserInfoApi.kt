package com.joseph.marketkurly_clone.src.activity_main.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface UserInfoApi {

    @GET("userInfo")
    fun loadUserInfo(): Call<JsonObject>
}