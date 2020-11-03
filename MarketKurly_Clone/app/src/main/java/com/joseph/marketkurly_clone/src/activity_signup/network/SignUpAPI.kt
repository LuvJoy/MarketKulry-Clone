package com.joseph.marketkurly_clone.src.activity_signup.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpAPI {

    @POST("user/checkID")
    fun checkDuplicateID(
            @Body id: JsonObject
    ): Call<JsonObject>
}