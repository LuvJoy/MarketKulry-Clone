package com.joseph.marketkurly_clone.src.activity_select_product.interfaces

interface PlusMinusButtonListener {
    fun onPlusClicked(count: Int, totalCost: Int, totalPoint: Int)
    fun onMinusClicked(count: Int, totalCost: Int, totalPoint: Int)
}