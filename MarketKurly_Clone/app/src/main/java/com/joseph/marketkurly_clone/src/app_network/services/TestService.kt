package com.joseph.marketkurly_clone.src.app_network.services

import com.joseph.marketkurly_clone.src.activity_main.models.TestResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface TestService {
    //    @GET("/test")
    @GET("/jwt")
    fun getTest(): Call<TestResponse>

    @GET("/test/{number}")
    fun getTestPathAndQuery(
        @Path("number") number: Int,
        @Query("content") content: String?
    ): Call<TestResponse>

    @POST("/test")
    fun postTest(@Body params: RequestBody?): Call<TestResponse>
}
