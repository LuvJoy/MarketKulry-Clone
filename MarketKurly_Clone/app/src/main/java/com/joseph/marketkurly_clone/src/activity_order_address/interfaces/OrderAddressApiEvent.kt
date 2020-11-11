package com.joseph.marketkurly_clone.src.activity_order_address.interfaces

import com.joseph.marketkurly_clone.src.activity_order_address.models.OrderAddressCombine

interface OrderAddressApiEvent {

    fun onGetOrderAddressSuccess(list: ArrayList<OrderAddressCombine>)
    fun onGetOrderAddressFail(message: String)
}