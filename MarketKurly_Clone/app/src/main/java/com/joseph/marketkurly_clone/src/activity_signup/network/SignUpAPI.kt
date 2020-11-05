package com.joseph.marketkurly_clone.src.activity_signup.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SignUpAPI {

    @GET("checkID?")
    fun checkDuplicateID(
            @Query("id") id: String
    ): Call<JsonObject>

    
    @GET("checkPhoneNumber?")
    fun checkPhoneNumber(
            @Query("phoneNumber") phoneNumber: String
    ): Call<JsonObject>
    
}