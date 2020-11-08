package com.joseph.marketkurly_clone.src.db

interface CartEvent {

    fun onCartAddedSuccess()
    fun onCartAddedFail()

    fun onCartLoadSuccess(list: List<Cart>?)
    fun onCartLoadFail()

    fun onCartSizeLoadSuccess(size: Int)
    fun onCartSizeLoadFail()
}