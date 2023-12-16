package ru.unisafe.retrofit.shopping_list

import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import ru.unisafe.retrofit.shopping_lists.ShoppingListsAPI
import ru.unisafe.retrofit.shopping_lists.entities.CreateShoppingListResponse
import ru.unisafe.retrofit.shopping_lists.entities.GetAllShoppingListsResponse
import ru.unisafe.retrofit.shopping_lists.entities.ShoppingListDTO
import kotlin.random.Random

class ShoppingListsAPITestStub(private val isKeyCorrect: Boolean = true) : ShoppingListsAPI {

    var createShoppingListResponse: CreateShoppingListResponse? = null
    var getAllShoppingListsResponse: GetAllShoppingListsResponse? = null
    var listIsRemoved: Boolean = false

    override suspend fun createShoppingList(key: String, name: String): CreateShoppingListResponse {
        createShoppingListResponse = CreateShoppingListResponse(Random.nextInt(0, 10000), true)
        return createShoppingListResponse!!

    }

    override suspend fun removeShoppingList(listId: Int): ResponseBody {
        listIsRemoved = true
        return ResponseBody.create(MediaType.get("application/json"), "")
    }

    override suspend fun getAllShoppingLists(key: String): GetAllShoppingListsResponse {
        if (!isKeyCorrect) throw HttpException(
            Response.error<Any>(
                406, ResponseBody.create(MediaType.get("text/plain"), "")
            )
        )
        getAllShoppingListsResponse = GetAllShoppingListsResponse(
            listOf(
                ShoppingListDTO(
                    Random.nextInt(0, 10000), "Имя", "12.12.2020"
                )
            ), true
        )
        return getAllShoppingListsResponse!!
    }
}