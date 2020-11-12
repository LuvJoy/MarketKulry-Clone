package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_search.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface SearchApi {

    @GET("popular-keyword")
    fun getPopularQuery() : Call<JsonObject>
}