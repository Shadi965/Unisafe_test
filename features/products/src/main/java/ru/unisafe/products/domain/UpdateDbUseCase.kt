package ru.unisafe.products.domain

import ru.unisafe.products.domain.repositories.ProductsRepository
import javax.inject.Inject

class UpdateDbUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {

    suspend fun updateDb(listId: Int) = productsRepository.updateDb(listId)

}