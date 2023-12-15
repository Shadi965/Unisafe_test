package ru.unisafe.products.domain.entities

data class Product(
    val id: Int,
    val name: String,
    val count: Int,
    val isCrossed: Boolean
)
