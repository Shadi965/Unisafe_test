package ru.unisafe.data.retrofit.products.entities

import com.squareup.moshi.Json

data class ProductCreateResponse(
    val success: Boolean,
    @Json(name = "item_id")
    val id: Int
)
