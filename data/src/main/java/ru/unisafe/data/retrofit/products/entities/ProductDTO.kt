package ru.unisafe.data.retrofit.products.entities

import com.squareup.moshi.Json
import ru.unisafe.data.products.entity.Product

data class ProductDTO(
    val id: Int,
    val name: String,
    @Json(name = "created")
    val count: String,
    @Json(name = "is_crossed")
    val isCrossed: Boolean
) {
    fun toProduct(): Product {
        return Product(
            id = id,
            name = name,
            count = count.toInt(),
            isCrossed = isCrossed
        )
    }
}
