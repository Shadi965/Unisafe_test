package ru.unisafe.room.products

import androidx.room.ColumnInfo

data class ProductGetTuple(
    val id: Int,
    val name: String,
    val count: Int,
    @ColumnInfo(name = "is_crossed")
    val isCrossed: Boolean
)

data class ProductDeleteTuple(
    @ColumnInfo(name = "shopping_list_id")
    val shoppingListId: Int,
    val id: Int
)

data class ProductCrossOffTuple(
    @ColumnInfo(name = "shopping_list_id")
    val shoppingListId: Int,
    val id: Int,
    @ColumnInfo(name = "is_crossed")
    val isCrossed: Boolean
)