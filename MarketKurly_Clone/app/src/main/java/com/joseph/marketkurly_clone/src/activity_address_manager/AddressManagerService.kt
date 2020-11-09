package com.joseph.marketkurly_clone.src.activity_address_manager

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.joseph.marketkurly_clone.NetworkConstants.KURLY_URL
import com.joseph.marketkurly_clone.RetrofitClient
import com.joseph.marketkurly_clone.src.activity_address_manager.interfaces.UserAddressApiEvent
import com.joseph.marketkurly_clone.src.activity_address_manager.models.UserAddress
import com.joseph.marketkurly_clone.src.activity_address_manager.network.UserAddressApi
import com.joseph.marketkurly_clone.src.activity_signup.interfaces.AddresApiEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressManagerService(private var listener: UserAddressApiEvent) {

    private var mRetrofit = RetrofitClient.getClient(KURLY_URL).create(UserAddressApi::class.java)

    fun getUserAddress() {
        mRetrofit.getAddress().enqueue(object: Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val body = response.body()
                val isSuccess = body?.get("is_success")?.asBoolean
                val addressList = ArrayList<UserAddress>()

                if(isSuccess!!) {
                    val result = body.get("result").asJsonArray
                    result.forEach {
                        addressList.add(Gson().fromJson(it, UserAddress::class.java))
                    }

                    listener.onLoadUserAddressSuccess(addressList)
                } else {
                    listener.onLoadUserAddressFail("잠시 후 다시 시도해 주세요")
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                listener.onLoadUserAddressFail(t.message!!)
            }

        })
    }
}