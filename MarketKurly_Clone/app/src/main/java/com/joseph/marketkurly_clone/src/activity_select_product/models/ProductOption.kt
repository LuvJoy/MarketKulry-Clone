package com.joseph.marketkurly_clone.src.activity_select_product.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductOption(
    @SerializedName("cost")
    var cost: Int,
    @SerializedName("discount_cost")
    var discountCost: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("option_idx")
    var optionIdx: Int,
    @SerializedName("product_id")
    var productId: Int,
    @SerializedName("sold_out")
    var soldOut: String
): Serializable