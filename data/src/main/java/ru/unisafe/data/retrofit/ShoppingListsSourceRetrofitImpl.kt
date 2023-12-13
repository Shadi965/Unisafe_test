package ru.unisafe.data.retrofit

import ru.unisafe.data.retrofit.shoping_lists.ShoppingListsAPI
import ru.unisafe.data.retrofit.shoping_lists.entities.ShoppingListDTO
import ru.unisafe.data.shopping_lists.ShoppingListsSource
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