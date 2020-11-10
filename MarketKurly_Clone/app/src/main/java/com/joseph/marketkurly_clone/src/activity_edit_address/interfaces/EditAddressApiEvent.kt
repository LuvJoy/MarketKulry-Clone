package com.joseph.marketkurly_clone.src.activity_edit_address.interfaces

interface EditAddressApiEvent {

    fun onEditSuccess()
    fun onEditFail(message: String)

    fun onRemoveSuccess()
    fun onRemoveFail(message: String)
}