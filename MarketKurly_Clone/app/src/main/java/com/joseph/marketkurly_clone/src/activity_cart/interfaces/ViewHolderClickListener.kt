package com.joseph.marketkurly_clone.src.activity_cart.interfaces

import com.joseph.marketkurly_clone.src.db.Cart

interface ViewHolderClickListener {
    fun onRemoveButtonClicked (item: Cart, position: Int, adapterName: String)
    fun onCheckBoxClicked (isChecked: Boolean, position: Int, adapterName: String)
    fun onPlusButtonClicked(count: Int, position: Int, adapterName: String)
    fun onMinusButtonClicked(count: Int, position: Int, adapterName: String)
}