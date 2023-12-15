package ru.unisafe.data.retrofit

import ru.unisafe.data.products.ProductsSource
import ru.unisafe.data.products.entity.Product
import ru.unisafe.data.retrofit.products.ProductsAPI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsSourceRetrofitImpl @Inject constructor(
    private val productsAPI: ProductsAPI
) : ProductsSource {
    override suspend fun getProductsList(listId: Int): List<Product> {
        return productsAPI.getShoppingList(listId).itemList.map { it.toProduct() }
    }

    override suspend fun addNewProductToList(listId: Int, productName: String, count: Int): Int {
        return productsAPI.addItemToShoppingList(listId, productName, count).id
    }

    override suspend fun removeProductFromList(productId: Int, listId: Int) {
        productsAPI.removeItemFromList(listId, productId)
    }

    override suspend fun crossProductOff(productId: Int) {
        productsAPI.crossItemOff(productId)
    }
}