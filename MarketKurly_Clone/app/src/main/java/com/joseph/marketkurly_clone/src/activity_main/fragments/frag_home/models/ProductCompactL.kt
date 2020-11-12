package com.joseph.marketkurly_clone.src.activity_main.fragments.frag_home.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductCompactL(
    @SerializedName("cost")
    var cost: Int,
    @SerializedName("discount_cost")
    var discountCost: Int,
    @SerializedName("discount_percent")
    var discountPercent: Int,
    @SerializedName("label")
    var label: List<String>,
    @SerializedName("name")
    var name: String,
    @SerializedName("product_id")
    var productId: Int,
    @SerializedName("thumbnail_url")
    var thumbnailUrl: String
):Serializable