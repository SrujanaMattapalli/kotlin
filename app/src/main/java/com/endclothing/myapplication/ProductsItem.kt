package com.endclothing.myapplication

import com.google.gson.annotations.SerializedName

class ProductsItem {

    @SerializedName("image")
    var image: String? = null

    @SerializedName("price")
    var price: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id: String? = null

    override fun toString(): String {
        return "ProductsItem{" +
                "image = '" + image + '\''.toString() +
                ",price = '" + price + '\''.toString() +
                ",name = '" + name + '\''.toString() +
                ",id = '" + id + '\''.toString() +
                "}"
    }
}