package com.joseph.marketkurly_clone.src.activity_edit_address.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.PATCH
import retrofit2.http.POST

interface EditAddressApi {

    @PATCH("address")
    fun editAddress() : Call<JsonObject>

}