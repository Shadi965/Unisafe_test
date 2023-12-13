package ru.unisafe.data.shopping_lists.entities

import com.squareup.moshi.Json

data class GetAllShoppingListsResponse(
    @Json(name = "shop_list")
    val shopLists: List<ShoppingListDTO>,
    @Json(name = "success")
    val status: Boolean
)