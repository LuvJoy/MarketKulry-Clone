package com.joseph.marketkurly_clone.src.activity_address_manager.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface UserAddressApi {

    @GET("address")
    fun getAddress() : Call<JsonObject>
}