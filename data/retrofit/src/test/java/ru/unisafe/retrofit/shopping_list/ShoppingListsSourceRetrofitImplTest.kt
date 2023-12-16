package ru.unisafe.retrofit.shopping_list

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.HttpException
import ru.unisafe.retrofit.shopping_lists.ShoppingListsSourceRetrofitImpl

class ShoppingListsSourceRetrofitImplTest {

    @Test
    fun getAllShoppingListsReturnedTheListFromShoppingListsAPI() = runBlocking {
        val shoppingListAPI = ShoppingListsAPITestStub()
        val shoppingListsSource = ShoppingListsSourceRetrofitImpl(shoppingListAPI)

        val listId = shoppingListsSource.getAllShoppingLists("Stub")[0].id
        val actualListId = shoppingListAPI.getAllShoppingListsResponse?.shopLists?.get(0)?.id ?: -1

        assertEquals(actualListId, listId)
    }

    @Test
    fun getAllShoppingListWithIncorrectKeyThrowHttpException406FromShoppingListsAPI(): Unit =
        runBlocking {
            val shoppingListAPI = ShoppingListsAPITestStub(isKeyCorrect = false)
            val shoppingListsSource = ShoppingListsSourceRetrofitImpl(shoppingListAPI)

            assertThrows(HttpException::class.java) {
                runBlocking {
                    shoppingListsSource.getAllShoppingLists("Stub")
                }
            }
        }

    @Test
    fun removeShoppingListCalledShoppingListAPIFun() = runBlocking {
        val shoppingListAPI = ShoppingListsAPITestStub()
        val shoppingListsSource = ShoppingListsSourceRetrofitImpl(shoppingListAPI)

        shoppingListsSource.removeShoppingList(0)
        val funcIsCalled = shoppingListAPI.listIsRemoved

        assertTrue(funcIsCalled)
    }

    @Test
    fun createShoppingListCalledShoppingListAPIFun() = runBlocking {
        val shoppingListAPI = ShoppingListsAPITestStub()
        val shoppingListsSource = ShoppingListsSourceRetrofitImpl(shoppingListAPI)

        val createdListId = shoppingListsSource.createShoppingList("Stub", "Stub")
        val actualCreatedListId = shoppingListAPI.createShoppingListResponse?.listId ?: -1

        assertEquals(actualCreatedListId, createdListId)
    }

}