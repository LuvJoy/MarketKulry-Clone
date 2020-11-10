package com.joseph.marketkurly_clone.src.activity_signup.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface SignUpApi {

    @GET("check-id?")
    fun checkDuplicateID(
            @Query("id") id: String
    ): Call<JsonObject>

    @GET("check-phonenumber?")
    fun checkPhoneNumber(
            @Query("phonenumber") phoneNumber: String
    ): Call<JsonObject>

    @POST("user")
    fun signUp(
            @Body user: JsonObject,
            @Header("Content-Type") content_type : String = "application/json"
    ): Call<JsonObject>
    
}