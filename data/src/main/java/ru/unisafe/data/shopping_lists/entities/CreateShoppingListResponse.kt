package ru.unisafe.data.shopping_lists.entities

import com.squareup.moshi.Json

data class CreateShoppingListResponse(
    @Json(name = "list_id")
    val listId: Int,
    @Json(name = "success")
    val status: Boolean
)
