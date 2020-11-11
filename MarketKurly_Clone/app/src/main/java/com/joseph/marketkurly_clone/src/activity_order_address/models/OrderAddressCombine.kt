package com.joseph.marketkurly_clone.src.activity_order_address.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OrderAddressCombine(
    @SerializedName("address")
    var address: String,
    @SerializedName("address_id")
    var addressId: Int,
    @SerializedName("delivery")
    var delivery: String,
    @SerializedName("is_basic")
    var isBasic: String,
    @SerializedName("is_chosen")
    var isChosen: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("phone_number")
    var phoneNumber: String,
    @SerializedName("place")
    var place: String
): Serializable