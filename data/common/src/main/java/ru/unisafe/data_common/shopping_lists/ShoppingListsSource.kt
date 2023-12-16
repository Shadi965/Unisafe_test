package ru.unisafe.data_common.shopping_lists

import ru.unisafe.data_common.shopping_lists.entities.ShoppingList

interface ShoppingListsSource {

    suspend fun getAllShoppingLists(key: String): List<ShoppingList>

    suspend fun createShoppingList(key: String, name: String): Int

    suspend fun removeShoppingList(listId: Int)

}