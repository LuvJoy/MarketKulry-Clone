package com.joseph.marketkurly_clone.src.activity_order.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Order(
    @SerializedName("payment_method")
    var paymentMethod: String,
    @SerializedName("total_product")
    var totalProductCost: Int,
    @SerializedName("delivery_fee")
    var deliveryCost: Int,
    @SerializedName("total_discount")
    var totalDiscountCost: Int,
    @SerializedName("total_cost")
    var totalCost: Int,
    @SerializedName("points")
    var points: Int?,
    @SerializedName("used_points")
    var usedPoints: Int?,
    @SerializedName("used_coupon")
    var usedCoupon: Int?,
    @SerializedName("coupon_discount")
    var couponDiscountCost: Int?,
    @SerializedName("name")
    var name: String,
    @SerializedName("phone_number")
    var phoneNumber: String,
    @SerializedName("post_code")
    var postCode: String,
    @SerializedName("address")
    var address: String,
    @SerializedName("address_detail")
    var addressDetail: String,
    @SerializedName("morning_delivery")
    var morningDelivery: String,
    @SerializedName("place")
    var place: String,
    @SerializedName("entrance_pw")
    var entrancePw: String?,
    @SerializedName("entrance_free")
    var entranceFree: String?,
    @SerializedName("entrance_etc")
    var entranceEtc: String?,
    @SerializedName("security_info")
    var securityInfo: String?,
    @SerializedName("mailbox_info")
    var mailBoxInfo: String?,
    @SerializedName("etc_info")
    var edtInfo: String?,
    @SerializedName("message")
    var message: String,
): Serializable
