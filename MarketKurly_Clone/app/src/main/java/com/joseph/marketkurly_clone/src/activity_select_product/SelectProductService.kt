package com.joseph.marketkurly_clone.src.activity_select_product

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.joseph.marketkurly_clone.Constants.TAG
import com.joseph.marketkurly_clone.NetworkConstants.KURLY_URL
import com.joseph.marketkurly_clone.RetrofitClient
import com.joseph.marketkurly_clone.src.activity_select_product.interfaces.ProductOptionApiEvent
import com.joseph.marketkurly_clone.src.activity_select_product.models.ProductOption
import com.joseph.marketkurly_clone.src.activity_select_product.network.ProductOptionApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectProductService(private var listener: ProductOptionApiEvent) {

    private var mRetrofit = RetrofitClient.getClient(KURLY_URL).create(ProductOptionApi::class.java)

    fun loadProductOption(productId: Int) {
        mRetrofit.loadProductOption(productId).enqueue(object : Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val body = response.body()
                val isSuccess = body?.get("is_success")?.asBoolean
                var optionList = ArrayList<ProductOption>()

                if(isSuccess!!) {
                    val result = body.get("result").asJsonArray
                    result.forEach {
                        Log.d(TAG, "[SelectProductService] - onResponse() : ${it.toString()}")
                        optionList.add(Gson().fromJson(it, ProductOption::class.java))
                    }
                    listener.onLoadSuccess(optionList)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {

            }

        })
    }
}