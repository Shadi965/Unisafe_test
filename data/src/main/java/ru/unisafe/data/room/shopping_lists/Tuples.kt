package ru.unisafe.data.room.shopping_lists

import androidx.room.ColumnInfo

data class ShoppingListGetTuple(
    val id: Int,
    val name: String,
    @ColumnInfo(name = "created_at")
    val createdAt: String
)

data class ShoppingListDeleteTuple(
    val id: Int
)

data class ShoppingListsDeleteByKeyTuple(
    val key: String
)