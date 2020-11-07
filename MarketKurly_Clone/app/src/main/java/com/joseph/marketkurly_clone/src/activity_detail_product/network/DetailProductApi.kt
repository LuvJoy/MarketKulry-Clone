package com.joseph.marketkurly_clone.src.activity_detail_product.network

import com.google.gson.JsonObject
import com.joseph.marketkurly_clone.src.activity_detail_product.models.ProductDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailProductApi {

    @GET("products/{productId}/description")
    fun loadProductDetail(
            @Path("productId")productId: Int
    ): Call<JsonObject>
}