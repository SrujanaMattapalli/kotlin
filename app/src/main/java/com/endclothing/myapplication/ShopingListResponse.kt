package com.endclothing.myapplication

import com.google.gson.annotations.SerializedName

class ShopingListResponse {

    @SerializedName("product_count")
    var productCount: Int = 0

    @SerializedName("title")
    var title: String? = null

    @SerializedName("products")
    var products: List<ProductsItem>? = null

    override fun toString(): String {
        return "ShopingListResponse{" +
                "product_count = '" + productCount + '\''.toString() +
                ",title = '" + title + '\''.toString() +
                ",products = '" + products + '\''.toString() +
                "}"
    }
}