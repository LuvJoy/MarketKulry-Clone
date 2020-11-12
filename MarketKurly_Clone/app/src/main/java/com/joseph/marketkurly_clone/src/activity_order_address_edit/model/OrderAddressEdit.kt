package com.joseph.marketkurly_clone.src.activity_order_address_edit.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OrderAddressEdit(
    @SerializedName("address")
    var address: String,
    @SerializedName("address_detail")
    var addressDetail: String,
    @SerializedName("delivery")
    var delivery: String,
    @SerializedName("entrance_etc")
    var entranceEtc: String,
    @SerializedName("entrance_free")
    var entranceFree: String,
    @SerializedName("entrance_pw")
    var entrancePw: String,
    @SerializedName("etc_info")
    var etcInfo: String,
    @SerializedName("mailbox_info")
    var mailboxInfo: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("phone_number")
    var phoneNumber: String,
    @SerializedName("place")
    var place: String,
    @SerializedName("place_info")
    var placeInfo: String,
    @SerializedName("security_info")
    var securityInfo: String,
    @SerializedName("post_code")
    var postCode: String? = null
) : Serializable