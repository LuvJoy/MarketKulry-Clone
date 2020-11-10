package com.joseph.marketkurly_clone.src.db


import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Cart", primaryKeys = ["productId", "optionIdx"])
data class Cart(
    @SerializedName("product_id")
    var productId: Int,
    @SerializedName("option_idx")
    var optionIdx: Int,
    @SerializedName("cost")
    var cost: Int,
    @SerializedName("count")
    var count: Int,
    @SerializedName("discount_cost")
    var discountCost: Int,
    @SerializedName("option_name")
    var optionName: String,
    @SerializedName("package")
    var packageX: String,
    @SerializedName("product_name")
    var productName: String?,
    @SerializedName("sold_out")
    var soldOut: String,
    @SerializedName("thumbnail_url")
    var thumbnailUrl: String
): Serializable