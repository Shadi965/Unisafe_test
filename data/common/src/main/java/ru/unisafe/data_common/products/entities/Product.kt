package ru.unisafe.data_common.products.entities

data class Product(
    val id: Int,
    val name: String,
    val count: Int,
    val isCrossed: Boolean
)
