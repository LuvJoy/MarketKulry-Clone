package com.joseph.marketkurly_clone.src.activity_select_product.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductOptionApi {
    @GET("products/{productId}/options")
    fun loadProductOption(
        @Path("productId") productId: Int
    ): Call<JsonObject>

}