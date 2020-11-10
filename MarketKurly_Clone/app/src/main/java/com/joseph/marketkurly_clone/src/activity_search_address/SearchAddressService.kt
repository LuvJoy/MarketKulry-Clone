package com.joseph.marketkurly_clone.src.activity_search_address

import com.google.gson.JsonObject
import com.joseph.marketkurly_clone.NetworkConstants.KURLY_URL
import com.joseph.marketkurly_clone.RetrofitClient
import com.joseph.marketkurly_clone.src.activity_search_address.interfaces.SearchAddressApiEvent
import com.joseph.marketkurly_clone.src.activity_search_address.models.Address
import com.joseph.marketkurly_clone.src.activity_search_address.network.SearchAddressApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchAddressService(private var listener: SearchAddressApiEvent) {

    private var mRetrofit = RetrofitClient.getClient(KURLY_URL).create(SearchAddressApi::class.java)

    fun addAddress(address: Address) {

        val json = JsonObject()
        json.apply{
            addProperty("post_code", address.postCode)
            addProperty("address", address.address)
            addProperty("address_detail", address.addressDetail)
            addProperty("is_basic", address.isDefault)
            addProperty("morning_delivery", address.isStarShipping)
        }

        mRetrofit.addAddress(json).enqueue(object : Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val body = response.body()
                val isSuccess = body?.get("is_success")?.asBoolean

                if(isSuccess!!){
                    listener.onAddAddressSuccess()
                } else {
                    val message = body.get("message").asString
                    listener.onAddAddressFail(message)
                }

            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                listener.onAddAddressFail(t.message!!)
            }

        })
    }
}