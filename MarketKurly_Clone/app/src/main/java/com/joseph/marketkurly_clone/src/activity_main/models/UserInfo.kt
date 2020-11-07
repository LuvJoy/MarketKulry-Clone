package com.joseph.marketkurly_clone.src.activity_main.models


import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("cart_count")
    var cartCount: Int,
    @SerializedName("coupon")
    var coupon: Int,
    @SerializedName("level")
    var level: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("point_percentage")
    var pointPercentage: String,
    @SerializedName("points")
    var points: Int
)