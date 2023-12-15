package ru.unisafe.data.retrofit.products.entities

import com.squareup.moshi.Json

data class GetProductsResponse(
    val success: Boolean,
    @Json(name = "item_list")
    val itemList: List<ProductDTO>
)
