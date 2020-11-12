package com.joseph.marketkurly_clone.src.activity_search_result

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.joseph.marketkurly_clone.NetworkConstants.KURLY_URL
import com.joseph.marketkurly_clone.RetrofitClient
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.models.ProductCompactL
import com.joseph.marketkurly_clone.src.activity_search_result.interfaces.SearchResultApiEvent
import com.joseph.marketkurly_clone.src.activity_search_result.network.SearchResultApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultService(private var listener: SearchResultApiEvent) {

    private var mRetrofit = RetrofitClient.getClient(KURLY_URL).create(SearchResultApi::class.java)

    fun getQueryResult(query: String) {
        mRetrofit.getQueryResult(query).enqueue(object :Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val body = response.body()
                val isSuccess = body?.get("is_success")?.asBoolean
                var productList = ArrayList<ProductCompactL>()

                if(isSuccess!!) {
                    val result = body.get("products").asJsonArray
                    result.forEach {
                        productList.add(Gson().fromJson(it, ProductCompactL::class.java))
                    }
                    listener.onQueryResultSuccess(productList)
                } else {
                    val message = body.get("message").asString
                    listener.onQueryResultFail(message)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                listener.onQueryResultFail(t.message!!)
            }

        })
    }
}