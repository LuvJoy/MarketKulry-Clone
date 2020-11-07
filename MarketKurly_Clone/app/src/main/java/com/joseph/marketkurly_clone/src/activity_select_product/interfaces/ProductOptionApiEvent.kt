package com.joseph.marketkurly_clone.src.activity_select_product.interfaces

import com.joseph.marketkurly_clone.src.activity_select_product.models.ProductOption

interface ProductOptionApiEvent {
    fun onLoadSuccess(optionList: ArrayList<ProductOption>)
    fun onLoadFail(message: String)
}