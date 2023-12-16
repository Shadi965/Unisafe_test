package ru.unisafe.example.glue.products

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.unisafe.data_common.products.ProductsDataRepository
import ru.unisafe.products.domain.entities.Product
import ru.unisafe.products.domain.repositories.ProductsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsRepositoryIntermediateImpl @Inject constructor(
    private val productsDataRepository: ProductsDataRepository
) : ProductsRepository {
    override suspend fun getProducts(listId: Int): Flow<List<Product>?> {
        return productsDataRepository.getProductsList(listId).map { list ->
            list?.map {
                Product(
                    id = it.id,
                    name = it.name,
                    count = it.count,
                    isCrossed = it.isCrossed
                )
            }
        }
    }

    override suspend fun addProductToList(listId: Int, productName: String, count: Int) =
        productsDataRepository.addProductToList(listId, productName, count)

    override suspend fun removeProduct(productId: Int, listId: Int) =
        productsDataRepository.removeProduct(productId, listId)

    override suspend fun crossProductOff(listId: Int, productId: Int) =
        productsDataRepository.crossProductOff(listId, productId)

    override suspend fun updateDb(listId: Int) =
        productsDataRepository.updateDb(listId)
}