package com.joseph.marketkurly_clone.src.activity_login.network

import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AccountService {

    @POST("user")
    fun signIn(
            @Body user: JsonObject,
            @Header("Content-Type") Content_Type: String = "application/json"
            )
}