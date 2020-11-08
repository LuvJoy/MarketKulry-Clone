package com.joseph.marketkurly_clone.src.db

import android.util.Log
import com.joseph.marketkurly_clone.ApplicationClass
import com.joseph.marketkurly_clone.ApplicationClass.Companion.DB_CART
import com.joseph.marketkurly_clone.Constants.TAG
import kotlinx.coroutines.*

class CartService(private var listener: CartEvent) {

    private var job = SupervisorJob()
    private var coroutineScope = CoroutineScope(Dispatchers.Main + job)

    fun addCartItem(item: List<Cart>) {

        coroutineScope.launch {

            CoroutineScope(Dispatchers.IO).launch {
                for (i in item) {
                    Log.d(TAG, "[CartService] - addCartItem() : $i")
                    DB_CART?.cartDao()?.addCart(i)
                }
            }

            listener.onCartAddedSuccess()
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


    fun loadAllCart() {
        var cartList: List<Cart>? = listOf()

        coroutineScope.launch {
            cartList = withContext(Dispatchers.IO) {
                Log.d(TAG, "[CartService] - loadAllCart() : LOADING")
                DB_CART?.cartDao()?.loadAllCart()
            }
            listener.onCartLoadSuccess(cartList)
        }
    }


    fun onCleared() {
        job.cancel()
    }

}