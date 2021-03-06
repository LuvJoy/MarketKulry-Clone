package com.joseph.marketkurly_clone.src.activity_order.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OrderAddress(
    @SerializedName("address")
    var address: String,
    @SerializedName("address_id")
    var addressId: Int,
    @SerializedName("delivery")
    var delivery: String,
    @SerializedName("is_basic")
    var isBasic: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("phone_number")
    var phoneNumber: String,
    @SerializedName("place")
    var place: String,
    @SerializedName("place_detail")
    var placeDetail: String,
    @SerializedName("post_code")
    var postCode: String? = null
) : Serializable