package com.endclothing.myapplication.api

import com.endclothing.myapplication.ShopingListResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface GetShoppingItemsList {
    @GET("media/catalog/example.json")
    fun ShoppinglistResponse(): Call<ShopingListResponse>

    companion object Factory {
        val BASE_URL = "https://www.endclothing.com/"
        fun create(): GetShoppingItemsList {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(GetShoppingItemsList::class.java)
        }

    }
}