package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.fragments.frag_tab_reckurly.model

data class ProductCompact (
    var id: String,
    var title: String,
    var imageUrl: String,
    var price: Int,
    var salePrice: Int? = null,
    var salePercentage: Int? = null
) {
}