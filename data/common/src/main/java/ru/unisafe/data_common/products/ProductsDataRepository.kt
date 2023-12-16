package ru.unisafe.data_common.products

import kotlinx.coroutines.flow.Flow
import ru.unisafe.data_common.products.entities.Product

interface ProductsDataRepository {

    suspend fun getProductsList(listId: Int): Flow<List<Product>?>

    suspend fun addProductToList(listId: Int, productName: String, count: Int)

    suspend fun removeProduct(productId: Int, listId: Int)

    suspend fun crossProductOff(listId: Int, productId: Int)

    suspend fun updateDb(listId: Int)

}