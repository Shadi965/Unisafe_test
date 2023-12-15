package ru.unisafe.products.domain

import kotlinx.coroutines.flow.Flow
import ru.unisafe.products.domain.entities.Product
import ru.unisafe.products.domain.repositories.ProductsRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {

    suspend fun getList(listId: Int): Flow<List<Product>?> {
        return productsRepository.getProducts(listId)
    }

}