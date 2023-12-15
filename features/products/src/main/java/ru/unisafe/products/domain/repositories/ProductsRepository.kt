package ru.unisafe.products.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.unisafe.products.domain.entities.Product

interface ProductsRepository {

    suspend fun getProducts(listId: Int): Flow<List<Product>?>

    suspend fun addProductToList(listId: Int, productName: String, count: Int)

    suspend fun removeProduct(productId: Int, listId: Int)

    suspend fun crossProductOff(listId: Int, productId: Int)

    suspend fun updateDb(listId: Int)

}