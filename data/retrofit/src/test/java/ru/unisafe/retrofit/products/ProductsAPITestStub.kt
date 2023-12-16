package ru.unisafe.retrofit.products

import okhttp3.MediaType
import okhttp3.ResponseBody
import ru.unisafe.retrofit.products.entities.GetProductsResponse
import ru.unisafe.retrofit.products.entities.ProductCreateResponse
import ru.unisafe.retrofit.products.entities.ProductDTO
import kotlin.random.Random

class ProductsAPITestStub : ProductsAPI {

    var getProductsResponse: GetProductsResponse? = null
    var productsCreateResponse: ProductCreateResponse? = null
    var isItemRemoved = false
    var isItemCrossed = false

    override suspend fun getShoppingList(listId: Int): GetProductsResponse {
        getProductsResponse = GetProductsResponse(
            true, listOf(ProductDTO(Random.nextInt(0, 10000), "name", "1", false))
        )
        return getProductsResponse!!
    }

    override suspend fun addItemToShoppingList(
        listId: Int, itemName: String, quantityOfItem: Int
    ): ProductCreateResponse {
        productsCreateResponse = ProductCreateResponse(true, Random.nextInt(0, 10000))
        return productsCreateResponse!!
    }

    override suspend fun removeItemFromList(listId: Int, itemId: Int) {
        isItemRemoved = true
    }

    override suspend fun crossItemOff(itemId: Int): ResponseBody {
        isItemCrossed = true
        return ResponseBody.create(MediaType.get("text/plain"), "")
    }
}