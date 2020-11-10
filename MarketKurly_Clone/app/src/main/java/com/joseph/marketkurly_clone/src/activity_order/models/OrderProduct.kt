package com.joseph.marketkurly_clone.src.activity_order.models


import com.google.gson.annotations.SerializedName

data class OrderProduct(
    @SerializedName("cost")
    var cost: Int,
    @SerializedName("count")
    var count: Int,
    @SerializedName("discount_cost")
    var discountCost: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("option_name")
    var optionName: String,
    @SerializedName("thumbnail_url")
    var thumbnailUrl: String
)