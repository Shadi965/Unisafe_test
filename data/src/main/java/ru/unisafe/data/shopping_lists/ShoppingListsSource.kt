package ru.unisafe.data.shopping_lists

import ru.unisafe.data.retrofit.shoping_lists.entities.ShoppingListDTO

interface ShoppingListsSource {

    suspend fun getAllShoppingLists(key: String): List<ShoppingListDTO>

    suspend fun createShoppingList(key: String, name: String): Int

    suspend fun removeShoppingList(listId: Int)

}