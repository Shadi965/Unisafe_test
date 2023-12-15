package ru.unisafe.products.domain

import ru.unisafe.products.domain.repositories.ProductsRepository
import javax.inject.Inject

class RemoveProductFromListUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {

    suspend fun removeProductFromList(listId: Int, productId: Int) =
        productsRepository.removeProduct(productId, listId)

}