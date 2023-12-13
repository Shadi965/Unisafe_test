package ru.unisafe.shopping_lists.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.unisafe.shopping_lists.domain.entities.ShoppingList

interface ShoppingListsRepository {

    suspend fun deleteShoppingList(listId: Int)

    fun getShoppingListsByKey(key: String): Flow<List<ShoppingList>?>

}