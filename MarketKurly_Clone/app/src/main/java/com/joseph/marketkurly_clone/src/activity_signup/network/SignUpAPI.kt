package com.joseph.marketkurly_clone.src.activity_signup.network

import com.google.gson.JsonObject
import com.joseph.marketkurly_clone.src.activity_signup.models.UserInfo
import retrofit2.Call
import retrofit2.http.*

interface SignUpAPI {

    @GET("checkID?")
    fun checkDuplicateID(
            @Query("id") id: String
    ): Call<JsonObject>

    
    @GET("checkPhoneNumber?")
    fun checkPhoneNumber(
            @Query("phoneNumber") phoneNumber: String
    ): Call<JsonObject>

    @POST("user")
    fun signUp(
            @Body user: JsonObject,
            @Header("Content-Type") content_type : String = "application/json"
    ): Call<JsonObject>
    
}