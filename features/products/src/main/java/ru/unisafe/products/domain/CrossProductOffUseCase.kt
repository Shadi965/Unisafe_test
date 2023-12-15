package ru.unisafe.products.domain

import ru.unisafe.products.domain.repositories.ProductsRepository
import javax.inject.Inject

class CrossProductOffUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {

    suspend fun crossProductOff(listId: Int, productId: Int) =
        productsRepository.crossProductOff(listId, productId)

}