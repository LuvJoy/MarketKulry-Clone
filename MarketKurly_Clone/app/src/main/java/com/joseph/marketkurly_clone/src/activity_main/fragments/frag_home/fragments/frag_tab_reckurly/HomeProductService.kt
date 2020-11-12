package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.joseph.marketkurly_clone.NetworkConstants.KURLY_URL
import com.joseph.marketkurly_clone.RetrofitClient
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.interfaces.HomeProductApiEvent
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.network.HomeProductApi
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.models.ProductCompact
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.models.ProductCompactL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeProductService(private var listener: HomeProductApiEvent) {

    private var mRetrofit = RetrofitClient.getClient(KURLY_URL).create(HomeProductApi::class.java)

    fun getRecommendProduct() {
        mRetrofit.getRecommendProduct().enqueue(object : Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val body = response.body()
                val isSuccess = body?.get("is_success")?.asBoolean
                var productList = ArrayList<ProductCompact>()

                if(isSuccess!!) {
                    val result = body.get("result").asJsonArray
                    result.forEach {
                        productList.add(Gson().fromJson(it, ProductCompact::class.java))
                    }
                    listener.onProductLoadSuccess(productList)

                } else {
                    val message = body.get("message").asString
                    listener.onProductLoadFail(message)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                listener.onProductLoadFail(t.message ?: "")
            }

        })
    }

    fun getNewProduct(){
        mRetrofit.getNewProduct().enqueue(object : Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val body = response.body()
                val isSuccess = body?.get("is_success")?.asBoolean
                var productList = ArrayList<ProductCompactL>()

                if(isSuccess!!) {
                    val result = body.get("products").asJsonArray
                    result.forEach {
                        productList.add(Gson().fromJson(it, ProductCompactL::class.java))
                    }
                    listener.onLProductLoadSuccess(productList)

                } else {
                    val message = body.get("message").asString
                    listener.onLProductLoadFail(message)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                listener.onLProductLoadFail(t.message ?: "")
            }

        })
    }

    fun getBestProduct() {
        mRetrofit.getBestProduct().enqueue(object : Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val body = response.body()
                val isSuccess = body?.get("is_success")?.asBoolean
                var productList = ArrayList<ProductCompactL>()

                if(isSuccess!!) {
                    val result = body.get("products").asJsonArray
                    result.forEach {
                        productList.add(Gson().fromJson(it, ProductCompactL::class.java))
                    }
                    listener.onLProductLoadSuccess(productList)

                } else {
                    val message = body.get("message").asString
                    listener.onLProductLoadFail(message)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                listener.onLProductLoadFail(t.message ?: "")
            }

        })
    }

    fun getSaleProduct() {
        mRetrofit.getSaleProduct().enqueue(object : Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val body = response.body()
                val isSuccess = body?.get("is_success")?.asBoolean
                var productList = ArrayList<ProductCompactL>()

                if(isSuccess!!) {
                    val result = body.get("products").asJsonArray
                    result.forEach {
                        productList.add(Gson().fromJson(it, ProductCompactL::class.java))
                    }
                    listener.onLProductLoadSuccess(productList)

                } else {
                    val message = body.get("message").asString
                    listener.onLProductLoadFail(message)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                listener.onLProductLoadFail(t.message ?: "")
            }

        })
    }

}