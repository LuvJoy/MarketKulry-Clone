package com.joseph.marketkurly_clone.src.activity_order_address_edit.interfaces

import com.joseph.marketkurly_clone.src.activity_order_address_edit.model.OrderAddressEdit

interface OrderAddressEditApiEvent {

    fun onEditDataLoadSuccess(data: OrderAddressEdit)
    fun onEditDataLoadFail(message: String)

}