package ru.unisafe.shopping_lists.domain

import kotlinx.coroutines.flow.Flow
import ru.unisafe.shopping_lists.domain.entities.ShoppingList
import ru.unisafe.shopping_lists.domain.repositories.KeyRepository
import ru.unisafe.shopping_lists.domain.repositories.ShoppingListsRepository
import javax.inject.Inject

class GetShoppingListsUseKeys @Inject constructor(
    private val keyRepository: KeyRepository,
    private val shoppingListsRepository: ShoppingListsRepository
) {

    suspend fun getShoppingLists(): Flow<List<ShoppingList>?> {
        val key = keyRepository.getCurrentKey()
        return shoppingListsRepository.getShoppingListsByKey(key)
    }

}