package ru.unisafe.example.glue.shopping_lists

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.unisafe.data.shopping_lists.ShoppingListsDataRepository
import ru.unisafe.shopping_lists.domain.entities.ShoppingList
import ru.unisafe.shopping_lists.domain.repositories.ShoppingListsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingListsIntermediateRepositoryImpl @Inject constructor(
    private val shoppingListsDataRepository: ShoppingListsDataRepository
): ShoppingListsRepository {
    override suspend fun getShoppingListsByKey(key: String): Flow<List<ShoppingList>?> {
        return shoppingListsDataRepository.getAllShoppingLists(key).map {
            it?.map { list ->
                ShoppingList(
                    id = list.id,
                    name = list.name,
                    createdAt = list.createdAt
                )
            }
        }
    }

    override suspend fun createShoppingList(name: String) {
        shoppingListsDataRepository.createShoppingList(name)
    }

    override suspend fun deleteShoppingList(listId: Int) {
        shoppingListsDataRepository.removeShoppingList(listId)
    }
}