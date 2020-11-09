package com.joseph.marketkurly_clone.src.db

import android.util.Log
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

            if(LOGIN_STATUS == Login.LOGGED) {
                val body = withContext(Dispatchers.IO){
                    mCartRetrofit.getCart().execute().body()
                }
                val isSuccess = body?.get("is_success")?.asBoolean

                if(isSuccess!!) {
                    val userCartsize = body.get("products").asJsonArray.size()
                    listener.onCartSizeLoadSuccess(size + userCartsize)
                }

            } else {
                listener.onCartSizeLoadSuccess(size)
            }
        }

    }

    fun deleteAllCart(items: List<Cart>) {
        coroutineScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                DB_CART?.cartDao()?.deleteAllCart()
            }

            if(LOGIN_STATUS == Login.LOGGED) {
                for(item in items) {
                    withContext(Dispatchers.IO){
                        mCartRetrofit.removeCart(item.productId, item.optionIdx).execute()
                    }
                }
            }
        }
    }


    fun loadAllCart() {
        var cartList: List<Cart>? = listOf()

        coroutineScope.launch {
            cartList = withContext(Dispatchers.IO) {
                Log.d(TAG, "[CartService] - loadAllCart() : LOADING")
                DB_CART?.cartDao()?.loadAllCart()
            }

            if(CURRENT_USER != null) {

            }
            listener.onCartLoadSuccess(cartList)
        }
    }


    fun onCleared() {
        job.cancel()
    }

}