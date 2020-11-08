package com.joseph.marketkurly_clone.src.activity_select_product.interfaces

import com.joseph.marketkurly_clone.src.activity_select_product.models.ProductOption

interface PlusMinusButtonListener {
    fun onPlusClicked(idx:ProductOption, count: Int, plusCost: Int, plusPoint: Int)
    fun onMinusClicked(idx: ProductOption, count: Int, minusCost: Int, minusPoint: Int)
}