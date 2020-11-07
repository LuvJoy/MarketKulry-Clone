package com.joseph.marketkurly_clone.src.activity_detail_product

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.joseph.marketkurly_clone.Constants.TAG
import com.joseph.marketkurly_clone.NetworkConstants.KURLY_URL
import com.joseph.marketkurly_clone.RetrofitClient
import com.joseph.marketkurly_clone.src.activity_detail_product.interfaces.LoadProductDetailEvent
import com.joseph.marketkurly_clone.src.activity_detail_product.models.ProductDetail
import com.joseph.marketkurly_clone.src.activity_detail_product.network.DetailProductApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailProductService(private var listener: LoadProductDetailEvent) {

    private val mRetrofit = RetrofitClient.getClient(KURLY_URL).create(DetailProductApi::class.java)

    fun loadProductDetail(productId: Int) {
        mRetrofit.loadProductDetail(productId).enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Log.d(TAG, "[DetailProductService] - onResponse() : 통신 성공")
                val body = response.body()
                val isSuccess =body?.get("is_success")?.asBoolean

                if(isSuccess!!) {
                    val results = body.get("result").asJsonObject
                    var data = Gson().fromJson(results, ProductDetail::class.java)
                    listener.onLoadDetailSuccess(data)
                } else {
                    val message = body.get("message").asString
                    listener.onLoadDetailFail(message)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.d(TAG, "[DetailProductService] - onFailure() : 통신 실패")
            }

        })
    }
}