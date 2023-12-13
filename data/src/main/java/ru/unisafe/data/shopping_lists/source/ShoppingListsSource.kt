package ru.unisafe.data.shopping_lists.source

import ru.unisafe.data.shopping_lists.entities.ShoppingListDTO

interface ShoppingListsSource {

    suspend fun getAllShoppingLists(key: String): List<ShoppingListDTO>

    suspend fun createShoppingList(key: String, name: String): Int

    suspend fun removeShoppingList(listId: Int)

}