package ru.unisafe.data.products.entity

data class Product(
    val id: Int,
    val name: String,
    val count: Int,
    val isCrossed: Boolean
)
