package ru.unisafe.shopping_lists.domain

import kotlinx.coroutines.flow.Flow
import ru.unisafe.shopping_lists.domain.entities.ShoppingList
import ru.unisafe.shopping_lists.domain.repositories.ShoppingListsKeyRepository
import ru.unisafe.shopping_lists.domain.repositories.ShoppingListsRepository
import javax.inject.Inject

class GetShoppingListsUseKeys @Inject constructor(
    private val shoppingListsKeyRepository: ShoppingListsKeyRepository,
    private val shoppingListsRepository: ShoppingListsRepository
) {

    suspend fun getShoppingLists(): Flow<List<ShoppingList>?> {
        val key = shoppingListsKeyRepository.getCurrentKey()
        return shoppingListsRepository.getShoppingListsByKey(key)
    }

}