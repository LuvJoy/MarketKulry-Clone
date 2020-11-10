package com.joseph.marketkurly_clone.src.activity_order

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.joseph.marketkurly_clone.ApplicationClass.Companion.LOGIN_STATUS
import com.joseph.marketkurly_clone.NetworkConstants.KURLY_URL
import com.joseph.marketkurly_clone.RetrofitClient
import com.joseph.marketkurly_clone.src.activity_order.interfaces.OrderApiEvnet
import com.joseph.marketkurly_clone.src.activity_order.models.OrderAddress
import com.joseph.marketkurly_clone.src.activity_order.models.OrderPrice
import com.joseph.marketkurly_clone.src.activity_order.models.OrderProduct
import com.joseph.marketkurly_clone.src.activity_order.models.OrderUserInfo
import com.joseph.marketkurly_clone.src.activity_order.network.OrderApi
import com.joseph.marketkurly_clone.src.db.Cart
import com.joseph.marketkurly_clone.src.models.Login
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderService(private var listener: OrderApiEvnet) {

    private var mRetrofit = RetrofitClient.getClient(KURLY_URL).create(OrderApi::class.java)

    fun getOrderSheet(cartList: ArrayList<Cart>) {
        val jsonObject = JsonObject()
        val products = JsonArray()

        cartList.forEach {
            val item = JsonObject()
            item.apply {
                addProperty("product_id", it.productId)
                addProperty("option_idx", it.optionIdx)
                addProperty("count", it.count)
            }
            products.add(item)
        }

        jsonObject.add("products", products)

        mRetrofit.getOrderSheet(jsonObject).enqueue(object: Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val body = response.body()
                val isSuccess = body?.get("is_success")?.asBoolean

                if(isSuccess!!) {
                    var orderList = ArrayList<OrderProduct>()
                    var userInfo: OrderUserInfo? = null
                    var address: OrderAddress? = null
                    var price: OrderPrice? = null

                    if(LOGIN_STATUS == Login.LOGGED) {
                        body.get("order_products").asJsonArray.forEach {
                            orderList.add(Gson().fromJson(it, OrderProduct::class.java))
                        }
                        userInfo = Gson().fromJson(body.get("user_info").asJsonObject, OrderUserInfo::class.java)
                        address = Gson().fromJson(body.get("address").asJsonObject,OrderAddress::class.java)
                        price = Gson().fromJson(body.get("price").asJsonObject, OrderPrice::class.java)

                        listener.onGetSheetSuccess(orderList, price, address, userInfo)
                    } else {
                        body.get("order_products").asJsonArray.forEach {
                            orderList.add(Gson().fromJson(it, OrderProduct::class.java))
                        }
                        price = Gson().fromJson(body.get("price").asJsonObject, OrderPrice::class.java)
                        listener.onGetSheetSuccess(orderList, price, null, null)
                    }
                } else {
                    val message = body.get("message").asString
                    listener.onGetSheetFail(message)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                listener.onGetSheetFail(t.message!!)
            }

        })
    }
}