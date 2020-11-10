package com.joseph.marketkurly_clone.src.activity_search_address.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Address(

    @SerializedName("post_code")
    var postCode: String,
    @SerializedName("address")
    var address: String,
    @SerializedName("address_detail")
    var addressDetail: String,
    @SerializedName("is_basic")
    var isDefault: String,
    @SerializedName("morning_delivery")
    var isStarShipping: String

): Serializable {
}