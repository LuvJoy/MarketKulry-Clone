package com.joseph.marketkurly_clone.src.activity_order.interfaces

import com.joseph.marketkurly_clone.src.activity_order.models.*

interface OrderApiEvnet {

    fun onGetSheetSuccess(
        products: ArrayList<OrderProduct>,
        price: OrderPrice,
        address: OrderAddress?,
        userInfo: OrderUserInfo?
    )

    fun onGetSheetFail(message: String)

    fun onOrderSuccess(order: Order)
    fun onOrderFail(message: String)
}