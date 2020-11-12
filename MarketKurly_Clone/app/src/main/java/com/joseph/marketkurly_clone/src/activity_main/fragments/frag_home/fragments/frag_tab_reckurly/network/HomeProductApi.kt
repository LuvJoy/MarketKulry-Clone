package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface HomeProductApi {

    @GET("recommended-products")
    fun getRecommendProduct() : Call<JsonObject>

    @GET("new-products")
    fun getNewProduct() : Call<JsonObject>

    @GET("best-products")
    fun getBestProduct() : Call<JsonObject>

    @GET("sale-products")
    fun getSaleProduct() : Call<JsonObject>
}