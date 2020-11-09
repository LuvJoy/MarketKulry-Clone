package com.joseph.marketkurly_clone.src.db

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface CartApi {

    @POST("cart")
    fun addCart(
        @Body item: JsonObject
    ): Call<JsonObject>

    @GET("cart")
    fun getCart(): Call<JsonObject>

    @DELETE("/cart/{productId}/{optionIdx}")
    fun removeCart(
        @Path("productId") productId: Int,
        @Path("optionIdx") optionIdx: Int
    ): Call<JsonObject>

    @PATCH("/cart")
    fun updateCart(
        @Body item: JsonObject
    ): Call<JsonObject>

}