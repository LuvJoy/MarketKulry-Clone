package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface HomeProductApi {

    @GET("recommendedProducts")
    fun getRecommendProduct() : Call<JsonObject>
}