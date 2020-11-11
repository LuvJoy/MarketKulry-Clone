package com.joseph.marketkurly_clone.src.activity_order_address_edit.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface OrderAddressEditApi {

    @GET("order/address/{addressId}")
    fun getLoadEditOrderAddress(
        @Path("addressId") addressId: Int
    ): Call<JsonObject>

}