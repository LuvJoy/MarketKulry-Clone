package com.joseph.marketkurly_clone.src.activity_order.models


import com.google.gson.annotations.SerializedName

data class OrderPrice(
    @SerializedName("delivery_fee")
    var deliveryFee: Int,
    @SerializedName("points")
    var points: Int?,
    @SerializedName("total_cost")
    var totalCost: Int,
    @SerializedName("total_discount")
    var totalDiscount: Int?,
    @SerializedName("total_product")
    var totalProduct: Int
)