package ru.unisafe.retrofit.products

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ProductsSourceRetrofitImplTest {

    @Test
    fun getProductsListReturnListFromProductsAPIExpectedTheSameId() = runBlocking {
        val productsAPI = ProductsAPITestStub()
        val productsSource = ProductsSourceRetrofitImpl(productsAPI)

        val productFromListId = productsSource.getProductsList(0)[0].id
        val actualProductId = productsAPI.getProductsResponse?.itemList?.get(0)?.id ?: -1

        assertEquals(actualProductId, productFromListId)
    }

    @Test
    fun addNewProductToListReturnedCreatedProductId() = runBlocking {
        val productsAPI = ProductsAPITestStub()
        val productsSource = ProductsSourceRetrofitImpl(productsAPI)

        val productId = productsSource.addNewProductToList(0, "Stub", 0)
        val actualProductId = productsAPI.productsCreateResponse?.id ?: -1

        assertEquals(actualProductId, productId)
    }

    @Test
    fun removeProductFromListCalledProductAPIFun() = runBlocking {
        val productsAPI = ProductsAPITestStub()
        val productsSource = ProductsSourceRetrofitImpl(productsAPI)

        productsSource.removeProductFromList(0)
        val isCalled = productsAPI.isItemRemoved

        assertTrue(isCalled)
    }

    @Test
    fun crossProductOffCalledProductAPIFun() = runBlocking {
        val productsAPI = ProductsAPITestStub()
        val productsSource = ProductsSourceRetrofitImpl(productsAPI)

        productsSource.crossProductOff(0)
        val isCalled = productsAPI.isItemCrossed

        assertTrue(isCalled)
    }

}