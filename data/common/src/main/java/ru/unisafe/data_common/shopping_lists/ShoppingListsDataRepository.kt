package ru.unisafe.data_common.shopping_lists

import kotlinx.coroutines.flow.Flow
import ru.unisafe.data_common.shopping_lists.entities.ShoppingList

interface ShoppingListsDataRepository {

    suspend fun getAllShoppingLists(key: String): Flow<List<ShoppingList>?>

    suspend fun createShoppingList(name: String)

    suspend fun removeShoppingList(listId: Int)

}