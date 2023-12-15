package ru.unisafe.data.products

import ru.unisafe.data.products.entity.Product

interface ProductsSource {

    suspend fun getProductsList(listId: Int): List<Product>

    suspend fun addNewProductToList(listId: Int, productName: String, count: Int): Int

    suspend fun removeProductFromList(productId: Int, listId: Int = 0)

    suspend fun crossProductOff(productId: Int)

}