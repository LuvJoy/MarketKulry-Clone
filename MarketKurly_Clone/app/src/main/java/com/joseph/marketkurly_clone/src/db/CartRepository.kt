package com.joseph.marketkurly_clone.src.db

import android.util.Log
import com.joseph.marketkurly_clone.ApplicationClass.Companion.DB_CART
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

object CartRepository {
    val TAG = "[ 로그 ]"
    fun addCartItem(vararg item: Cart) {

        CoroutineScope(Dispatchers.IO).launch {
            item.forEach {
                DB_CART?.cartDao()?.addCart(it)
                Log.d(TAG, "[CartRepository] - addCartItem() : ADDED")

            }
        }
    }


    fun countCartItem(): Int {
        var size = 0
        CoroutineScope(Dispatchers.Main).launch {
            var list = withContext(Dispatchers.IO) {
                DB_CART?.cartDao()?.loadAllCart()
            }
            size = list!!.size
        }
        return size
    }
}