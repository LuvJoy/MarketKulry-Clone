package com.joseph.marketkurly_clone.src.db

import android.content.ClipData
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.joseph.marketkurly_clone.ApplicationClass
import com.joseph.marketkurly_clone.ApplicationClass.Companion.CURRENT_USER
import com.joseph.marketkurly_clone.ApplicationClass.Companion.DB_CART
import com.joseph.marketkurly_clone.ApplicationClass.Companion.LOGIN_STATUS
import com.joseph.marketkurly_clone.Constants.TAG
import com.joseph.marketkurly_clone.NetworkConstants.KURLY_URL
import com.joseph.marketkurly_clone.RetrofitClient
import com.joseph.marketkurly_clone.src.models.Login
import kotlinx.coroutines.*

class CartService(private var listener: CartEvent) {

    private var job = SupervisorJob()
    private var coroutineScope = CoroutineScope(Dispatchers.Main + job)
    private var mCartRetrofit = RetrofitClient.getClient(KURLY_URL).create(CartApi::class.java)

    fun addCartItem(items: List<Cart>) {

        coroutineScope.launch {

            CoroutineScope(Dispatchers.IO).launch {
                for (i in items) {
                    Log.d(TAG, "[CartService] - addCartItem() : $i")
                    DB_CART?.cartDao()?.addCart(i)
                }
            }
            // 장바구니에 담고 유저인 상태면 서버에도 담기는것으로 처
            if (LOGIN_STATUS == Login.LOGGED) {

                val product = JsonObject()
                product.addProperty("product_id", items.first().productId)
                val products = JsonArray()
                product.add("products", products)

                for (i in items) {
                    val json = JsonObject().apply {
                        addProperty("option_idx", i.optionIdx)
                        addProperty("count", i.count)
                    }
                    products.add(json)
                }

                val isSuccess = withContext(Dispatchers.IO) {
                    val response = mCartRetrofit.addCart(product).execute()
                    response.body()?.get("is_success")?.asBoolean
                }

                if (isSuccess!!) {
                    Log.d(TAG, "[CartService] - addCartItem() : 서버 장바구니 담기 성공")
                    listener.onCartAddedSuccess()
                } else listener.onCartAddedFail()

            } else {
                listener.onCartAddedSuccess()
            }
        }
    }

    fun loadCartSize() {
        var size = 0
        coroutineScope.launch {
            var list = withContext(Dispatchers.IO) {
                DB_CART?.cartDao()?.loadAllCart()
            }
            size = list!!.size

            listener.onCartSizeLoadSuccess(size)

        }

    }

    fun deleteAllCart() {
        coroutineScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                DB_CART?.cartDao()?.deleteAllCart()
            }
        }
    }

    fun deleteCart(items: List<Cart>) {
        coroutineScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                for (item in items) {
                    DB_CART?.cartDao()?.deleteCart(item)
                }
            }

            if (LOGIN_STATUS == Login.LOGGED) {
                withContext(Dispatchers.IO) {
                    for (item in items) {
                        mCartRetrofit.removeCart(item.productId, item.optionIdx).execute()
                    }
                }
            }
        }
    }


    fun loadAllCart() {
        var dbCartList: List<Cart>? = listOf()

        coroutineScope.launch {
            dbCartList = withContext(Dispatchers.IO) {
                Log.d(TAG, "[CartService] - loadAllCart() : LOADING")
                DB_CART?.cartDao()?.loadAllCart()
            }

            if (LOGIN_STATUS == Login.LOGGED) {
                var response = withContext(Dispatchers.IO) {
                    mCartRetrofit.getCart().execute()
                }
                var isSuccess = response.body()?.get("is_success")?.asBoolean
                var serverCartList = ArrayList<Cart>()
                if (isSuccess!!) {
                    val list = response.body()?.get("products")?.asJsonArray
                    list?.forEach {
                        var item = Gson().fromJson(it, Cart::class.java)
                        Log.d(TAG, "[CartService] - loadAllCart() server item : $item")
                        serverCartList.add(item)
                    }

                    withContext(Dispatchers.IO) {
                        serverCartList.forEach {
                            DB_CART?.cartDao()?.addCart(it)
                        }
                    }

                    var unionList = withContext(Dispatchers.IO) {
                        DB_CART?.cartDao()?.loadAllCart()
                    }

                    listener.onCartLoadSuccess(unionList)
                } else {
                    listener.onCartLoadFail()
                }

            } else {
                listener.onCartLoadSuccess(dbCartList)
            }


        }
    }


    fun onCleared() {
        job.cancel()
    }

}