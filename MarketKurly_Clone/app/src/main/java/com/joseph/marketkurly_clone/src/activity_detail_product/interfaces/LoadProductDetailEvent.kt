package com.joseph.marketkurly_clone.src.activity_detail_product.interfaces

import com.joseph.marketkurly_clone.src.activity_detail_product.models.ProductDetail

interface LoadProductDetailEvent {

    fun onLoadDetailSuccess(detailData: ProductDetail)

    fun onLoadDetailFail(message: String)
}