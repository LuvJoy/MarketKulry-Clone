package com.joseph.marketkurly_clone.src.activity_signin.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface SignInApi {

    // 로그인 체크
    @POST("sign-in")
    fun signIn(
            @Body user: JsonObject,
            @Header("Content-Type") Content_Type: String = "application/json"
    ): Call<JsonObject>

}