package com.joseph.marketkurly_clone.src.activity_order.interfaces

import com.joseph.marketkurly_clone.src.activity_order.models.OrderAddress
import com.joseph.marketkurly_clone.src.activity_order.models.OrderPrice
import com.joseph.marketkurly_clone.src.activity_order.models.OrderProduct
import com.joseph.marketkurly_clone.src.activity_order.models.OrderUserInfo

interface OrderApiEvnet {

    fun onGetSheetSuccess(
        products: ArrayList<OrderProduct>,
        price: OrderPrice,
        address: OrderAddress?,
        userInfo: OrderUserInfo?
    )

    fun onGetSheetFail(message: String)
}