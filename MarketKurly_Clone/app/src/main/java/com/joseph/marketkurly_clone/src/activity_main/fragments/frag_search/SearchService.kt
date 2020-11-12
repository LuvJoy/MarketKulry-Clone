package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_search

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.joseph.marketkurly_clone.NetworkConstants.KURLY_URL
import com.joseph.marketkurly_clone.RetrofitClient
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_search.interfaces.SearchApiEvent
import com.joseph.marketkurly_clone.src.activity_main.fragments.frag_search.network.SearchApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchService(private var listener: SearchApiEvent) {

    private var mRetrofit = RetrofitClient.getClient(KURLY_URL).create(SearchApi::class.java)

    fun getQueryList() {
        mRetrofit.getPopularQuery().enqueue(object: Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val body = response.body()
                val isSuccess = body?.get("is_success")?.asBoolean
                var queryList = ArrayList<String>()

                if(isSuccess!!) {
                    val ja = body.get("keyword").asJsonArray
                    ja.forEach {
                        queryList.add(it.asString)
                    }
                    listener.onQueryLoadSuccess(queryList)

                } else {
                    val message = body.get("message").asString
                    listener.onQueryLoadFail(message)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                listener.onQueryLoadFail(t.message!!)
            }

        })
    }
}