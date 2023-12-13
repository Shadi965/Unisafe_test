package ru.unisafe.data.shopping_lists

import kotlinx.coroutines.flow.Flow
import ru.unisafe.data.retrofit.shoping_lists.entities.ShoppingListDTO

interface ShoppingListsDataRepository {

    suspend fun getAllShoppingLists(key: String): Flow<List<ShoppingListDTO>?>

    suspend fun createShoppingList(name: String)

    suspend fun removeShoppingList(listId: Int)

}