package com.joseph.marketkurly_clone.src.activity_detail_product.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductDetail(
    @SerializedName("allergy")
    var allergy: String,
    @SerializedName("cart_count")
    var cartCount: Int,
    @SerializedName("cost")
    var cost: Int,
    @SerializedName("delivery")
    var delivery: String,
    @SerializedName("description_img_url")
    var descriptionImgUrl: String,
    @SerializedName("detail_img_url")
    var detailImgUrl: String,
    @SerializedName("discount_cost")
    var discountCost: Int,
    @SerializedName("discount_percent")
    var discountPercent: Int,
    @SerializedName("expiration")
    var expiration: String,
    @SerializedName("guide")
    var guide: String,
    @SerializedName("level")
    var level: String,
    @SerializedName("livestock")
    var livestock: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("origin")
    var origin: String,
    @SerializedName("package")
    var packageX: String,
    @SerializedName("point")
    var point: Int,
    @SerializedName("product_id")
    var productId: Int,
    @SerializedName("review_count")
    var reviewCount: String,
    @SerializedName("short_content")
    var shortContent: String,
    @SerializedName("sweet")
    var sweet: String,
    @SerializedName("thumbnail_url")
    var thumbnailUrl: String,
    @SerializedName("unit")
    var unit: String,
    @SerializedName("weight")
    var weight: String
): Serializable