package com.joseph.marketkurly_clone.src.activity_order_address.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface OrderAddressApi {

    @GET("order/address")
    fun getOrderAddress(): Call<JsonObject>
}