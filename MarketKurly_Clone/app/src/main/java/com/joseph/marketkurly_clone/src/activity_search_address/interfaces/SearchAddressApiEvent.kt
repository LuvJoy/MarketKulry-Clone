package com.joseph.marketkurly_clone.src.activity_search_address.interfaces

interface SearchAddressApiEvent {

    fun onAddAddressSuccess()
    fun onAddAddressFail(message: String)
}