package com.joseph.marketkurly_clone.network.interfaces

import com.joseph.marketkurly_clone.model.TestResponse
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
