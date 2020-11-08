package com.joseph.marketkurly_clone.src.models


import com.google.gson.annotations.SerializedName

data class Cart(
    @SerializedName("cost")
    var cost: Int,
    @SerializedName("count")
    var count: Int,
    @SerializedName("discount_cost")
    var discountCost: Int,
    @SerializedName("option_name")
    var optionName: String,
    @SerializedName("product_id")
    var productId: Int,
    @SerializedName("product_name")
    var productName: String,
    @SerializedName("thumbnail_url")
    var thumbnailUrl: String
)