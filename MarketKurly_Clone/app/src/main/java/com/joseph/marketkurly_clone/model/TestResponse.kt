package com.joseph.marketkurly_clone.model

import com.google.gson.annotations.SerializedName

data class TestResponse(
    @SerializedName("code")
    private var code: Int,

    @SerializedName("message")
    private val message: String?,

    @SerializedName("isSuccess")
    private val isSuccess: Boolean

) {
    fun getCode(): Int {
        return code
    }

    fun getMessage(): String? {
        return message
    }

    fun getIsSuccess(): Boolean {
        return isSuccess
    }
}