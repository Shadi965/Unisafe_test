package ru.unisafe.data.shopping_lists.entities

import com.squareup.moshi.Json

data class ShoppingListDTO(
    val id: Int,
    val name: String,
    @Json(name = "created")
    val createdAt: String
)