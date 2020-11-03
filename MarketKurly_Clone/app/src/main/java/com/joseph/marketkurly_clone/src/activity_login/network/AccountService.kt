package com.joseph.marketkurly_clone.src.activity_login.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface AccountService {

    // 로그인 체크
    @POST("signIn")
    fun signIn(
            @Body user: JsonObject,
            @Header("Content-Type") Content_Type: String = "application/json"
    ): Call<JsonObject>

    @GET("products/{productId}/description")
    fun getProduct(
            @Path("productId") productID: Int
    ): Call<JsonObject>

}