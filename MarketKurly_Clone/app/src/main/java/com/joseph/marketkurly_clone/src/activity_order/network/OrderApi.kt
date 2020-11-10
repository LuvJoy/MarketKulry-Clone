package com.joseph.marketkurly_clone.src.activity_order.network

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface OrderApi {

    @POST("order-sheet")
    fun getOrderSheet(
        @Body jsonObject: JsonObject
    ): Call<JsonObject>
}