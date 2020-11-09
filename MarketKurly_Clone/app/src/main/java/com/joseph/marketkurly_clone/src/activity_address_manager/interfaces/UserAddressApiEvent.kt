package com.joseph.marketkurly_clone.src.activity_address_manager.interfaces

import com.joseph.marketkurly_clone.src.activity_address_manager.models.UserAddress

interface UserAddressApiEvent {

    fun onLoadUserAddressSuccess(list: ArrayList<UserAddress>)
    fun onLoadUserAddressFail(message: String)
}