package ru.unisafe.retrofit.shoping_lists

import ru.unisafe.data_common.shopping_lists.ShoppingListsSource
import ru.unisafe.data_common.shopping_lists.entities.ShoppingList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingListsSourceRetrofitImpl @Inject constructor(
    private val shoppingListsAPI: ShoppingListsAPI
) : ShoppingListsSource {
    override suspend fun getAllShoppingLists(key: String): List<ShoppingList> =
        shoppingListsAPI.getAllShoppingLists(key).shopLists.map {
            ShoppingList(
                id = it.id,
                name = it.name,
                createdAt = it.createdAt
            )
        }

    override suspend fun createShoppingList(key: String, name: String): Int =
        shoppingListsAPI.createShoppingList(key, name).listId

    override suspend fun removeShoppingList(listId: Int) {
        shoppingListsAPI.removeShoppingList(listId)
    }
}