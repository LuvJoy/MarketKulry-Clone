package com.joseph.marketkurly_clone.src.db


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Cart")
data class Cart(
    @PrimaryKey
    @SerializedName("product_id")
    var productId: Int,
    @SerializedName("cost")
    var cost: Int,
    @SerializedName("count")
    var count: Int,
    @SerializedName("discount_cost")
    var discountCost: Int,
    @SerializedName("option_name")
    var optionName: String,
    @SerializedName("product_name")
    var productName: String,
    @SerializedName("thumbnail_url")
    var thumbnailUrl: String
)