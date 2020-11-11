package com.joseph.marketkurly_clone.src.activity_order_address

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.joseph.marketkurly_clone.NetworkConstants.KURLY_URL
import com.joseph.marketkurly_clone.RetrofitClient
import com.joseph.marketkurly_clone.src.activity_order_address.interfaces.OrderAddressApiEvent
import com.joseph.marketkurly_clone.src.activity_order_address.models.OrderAddressCombine
import com.joseph.marketkurly_clone.src.activity_order_address.network.OrderAddressApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderAddressService(private var listener: OrderAddressApiEvent) {

    private var mRetrofit = RetrofitClient.getClient(KURLY_URL).create(OrderAddressApi::class.java)

    fun getOrderAddressList() {
        mRetrofit.getOrderAddress().enqueue(object : Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val body = response.body()
                val isSuccess = body?.get("is_success")?.asBoolean
                val listAddress = ArrayList<OrderAddressCombine>()
                if(isSuccess!!) {
                    val array = body?.get("address")?.asJsonArray
                    array?.forEach {
                        listAddress.add(Gson().fromJson(it, OrderAddressCombine::class.java))
                    }
                    listener.onGetOrderAddressSuccess(listAddress)
                } else{
                    val message = body.get("message").asString
                    listener.onGetOrderAddressFail(message)
                }

            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                listener.onGetOrderAddressFail(t.message!!)
            }

        })
    }
}