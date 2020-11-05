package com.joseph.marketkurly_clone.src.activity_signup.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserInfo(
    @SerializedName("address")
    var address: String,
    @SerializedName("address_detail")
    var addressDetail: String?,
    @SerializedName("birth")
    var birth: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("email_agree")
    var emailAgree: String,
    @SerializedName("event")
    var event: String? = null,
    @SerializedName("gender")
    var gender: String? = null,
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("password_check")
    var passwordCheck: String,
    @SerializedName("personal_agree")
    var personalAgree: String,
    @SerializedName("phone_number")
    var phoneNumber: String,
    @SerializedName("post_code")
    var postCode: String,
    @SerializedName("recommend_user_id")
    var recommendUserId: String? =null,
    @SerializedName("sms_agree")
    var smsAgree: String
): Serializable