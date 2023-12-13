package ru.unisafe.shopping_lists.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.unisafe.shopping_lists.domain.entities.ShoppingList

interface ShoppingListsRepository {

    suspend fun getShoppingListsByKey(key: String): Flow<List<ShoppingList>?>

    suspend fun createShoppingList(name: String)

    suspend fun deleteShoppingList(listId: Int)

}