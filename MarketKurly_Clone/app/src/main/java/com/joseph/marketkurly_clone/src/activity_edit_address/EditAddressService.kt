package com.joseph.marketkurly_clone.src.activity_edit_address

import com.google.gson.JsonObject
import com.joseph.marketkurly_clone.NetworkConstants.KURLY_URL
import com.joseph.marketkurly_clone.RetrofitClient
import com.joseph.marketkurly_clone.src.activity_address_manager.models.UserAddress
import com.joseph.marketkurly_clone.src.activity_edit_address.interfaces.EditAddressApiEvent
import com.joseph.marketkurly_clone.src.activity_edit_address.network.EditAddressApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditAddressService(private var listener: EditAddressApiEvent) {

    private var mRetrofit = RetrofitClient.getClient(KURLY_URL).create(EditAddressApi::class.java)

    fun editAddress(userAddress: UserAddress) {
        val json = JsonObject()
        json.apply {
            addProperty("address_id", userAddress.addressId)
            addProperty("address_detail", userAddress.addressDetail)
            addProperty("name", userAddress.name)
            addProperty("phone_number", userAddress.phoneNumber)
            addProperty("is_basic", userAddress.isBasic)
        }

        mRetrofit.editAddress().enqueue(object : Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val body = response.body()
                val isSuccess = body?.get("is_success")?.asBoolean

                if(isSuccess!!){
                    listener.onEditSuccess()
                } else {
                    val message = body.get("message").asString
                    listener.onEditFail(message)
                }

            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                listener.onEditFail(t.message!!)
            }


        })


    }
}