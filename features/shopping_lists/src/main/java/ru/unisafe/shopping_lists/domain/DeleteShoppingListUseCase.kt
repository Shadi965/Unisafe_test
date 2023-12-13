package ru.unisafe.shopping_lists.domain

import ru.unisafe.shopping_lists.domain.repositories.ShoppingListsRepository
import javax.inject.Inject

class DeleteShoppingListUseCase @Inject constructor(
    private val shoppingListsRepository: ShoppingListsRepository
) {

    suspend fun deleteShoppingList(listId: Int){
        shoppingListsRepository.deleteShoppingList(listId)
    }

}