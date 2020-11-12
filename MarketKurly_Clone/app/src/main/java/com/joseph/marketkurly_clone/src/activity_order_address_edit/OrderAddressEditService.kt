package com.joseph.marketkurly_clone.src.activity_order_address_edit

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.joseph.marketkurly_clone.NetworkConstants.KURLY_URL
import com.joseph.marketkurly_clone.RetrofitClient
import com.joseph.marketkurly_clone.src.activity_order_address_edit.interfaces.OrderAddressEditApiEvent
import com.joseph.marketkurly_clone.src.activity_order_address_edit.model.OrderAddressEdit
import com.joseph.marketkurly_clone.src.activity_order_address_edit.network.OrderAddressEditApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderAddressEditService(private var  listener: OrderAddressEditApiEvent) {

    private var mRetrofit = RetrofitClient.getClient(KURLY_URL).create(OrderAddressEditApi::class.java)

    fun loadEditData(addressId: Int) {
        mRetrofit.getLoadEditOrderAddress(addressId).enqueue(object : Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val body = response.body()
                val isSuccess = body?.get("is_success")?.asBoolean

                if(isSuccess!!) {
                    val data = Gson().fromJson(body.get("address").asJsonObject, OrderAddressEdit::class.java)
                    listener.onEditDataLoadSuccess(data)
                }else {
                    val message = body.get("message").asString
                    listener.onEditDataLoadFail(message)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                listener.onEditDataLoadFail(t.message!!)
            }

        })
    }
}

