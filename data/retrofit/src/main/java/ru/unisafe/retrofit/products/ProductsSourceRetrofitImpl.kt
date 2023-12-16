package ru.unisafe.retrofit.products

import ru.unisafe.data_common.products.ProductsSource
import ru.unisafe.data_common.products.entity.Product
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