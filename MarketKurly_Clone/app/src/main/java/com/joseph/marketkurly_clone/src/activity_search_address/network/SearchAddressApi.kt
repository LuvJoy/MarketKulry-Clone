package com.joseph.marketkurly_clone.src.activity_search_address.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SearchAddressApi {

    @POST("address")
    fun addAddress(
        @Body address: JsonObject
    ): Call<JsonObject>


}