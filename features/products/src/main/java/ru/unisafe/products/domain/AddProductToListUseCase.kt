package ru.unisafe.products.domain

import ru.unisafe.products.domain.repositories.ProductsRepository
import javax.inject.Inject

class AddProductToListUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {

    suspend fun addProductToList(listId: Int, name: String, count: Int) =
        productsRepository.addProductToList(listId, name, count)

}