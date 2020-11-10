package com.joseph.marketkurly_clone.src.activity_order.models


import com.google.gson.annotations.SerializedName

data class OrderUserInfo(
    @SerializedName("coupon")
    var coupon: Int,
    @SerializedName("email")
    var email: String,
    @SerializedName("level")
    var level: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("phone_number")
    var phoneNumber: String,
    @SerializedName("point_percentage")
    var pointPercentage: Int,
    @SerializedName("points")
    var points: Int
)