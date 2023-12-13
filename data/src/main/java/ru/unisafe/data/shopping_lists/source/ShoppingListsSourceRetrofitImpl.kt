package ru.unisafe.data.shopping_lists.source

import retrofit2.Retrofit
import ru.unisafe.data.shopping_lists.ShoppingListsAPI
import ru.unisafe.data.shopping_lists.entities.ShoppingListDTO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingListsSourceRetrofitImpl @Inject constructor(
    private val shoppingListsAPI: ShoppingListsAPI
) : ShoppingListsSource {
    override suspend fun getAllShoppingLists(key: String): List<ShoppingListDTO> =
        shoppingListsAPI.getAllShoppingLists(key).shopLists

    override suspend fun createShoppingList(key: String, name: String): Int =
        shoppingListsAPI.createShoppingList(key, name).listId

    override suspend fun removeShoppingList(listId: Int) {
        shoppingListsAPI.removeShoppingList(listId)
    }
}