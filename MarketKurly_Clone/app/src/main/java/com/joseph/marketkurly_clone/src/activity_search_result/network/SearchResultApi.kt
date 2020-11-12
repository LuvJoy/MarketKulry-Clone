package com.joseph.marketkurly_clone.src.activity_search_result.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SearchResultApi {
    @GET("products?")
    fun getQueryResult(
        @Query("keyword") query: String
    ): Call<JsonObject>
}