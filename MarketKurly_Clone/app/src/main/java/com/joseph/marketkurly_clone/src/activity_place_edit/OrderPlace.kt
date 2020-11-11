package com.joseph.marketkurly_clone.src.activity_place_edit


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OrderPlace(
    @SerializedName("entrance_etc")
    var entranceEtc: String?,
    @SerializedName("entrance_free")
    var entranceFree: String?,
    @SerializedName("entrance_pw")
    var entrancePw: String?,
    @SerializedName("etc_info")
    var etcInfo: String?,
    @SerializedName("mailbox_info")
    var mailboxInfo: String?,
    @SerializedName("place")
    var place: String?,
    @SerializedName("place_info")
    var placeInfo: String?,
    @SerializedName("security_info")
    var securityInfo: String?
): Serializable