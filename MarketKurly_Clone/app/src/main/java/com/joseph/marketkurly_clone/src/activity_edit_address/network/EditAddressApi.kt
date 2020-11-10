package com.joseph.marketkurly_clone.src.activity_edit_address.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface EditAddressApi {

    @PATCH("address")
    fun editAddress(
        @Body editedAddress: JsonObject
    ) : Call<JsonObject>

    @DELETE("address/{addressId}")
    fun removeAddress(
        @Path("addressId") addressId: Int
    ) : Call<JsonObject>
}