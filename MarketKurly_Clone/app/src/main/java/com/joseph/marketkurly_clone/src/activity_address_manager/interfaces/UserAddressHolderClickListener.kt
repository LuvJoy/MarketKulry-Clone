package com.joseph.marketkurly_clone.src.activity_address_manager.interfaces

import com.joseph.marketkurly_clone.src.activity_address_manager.models.UserAddress

interface UserAddressHolderClickListener {

    fun onCheckBoxClicked(userAddress: UserAddress)
}