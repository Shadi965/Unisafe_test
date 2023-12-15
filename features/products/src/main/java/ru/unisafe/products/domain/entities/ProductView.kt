package ru.unisafe.products.domain.entities

data class ProductView(
    val item: Product,
    val isChecked: Boolean,
    val isInProgress: Boolean
)
