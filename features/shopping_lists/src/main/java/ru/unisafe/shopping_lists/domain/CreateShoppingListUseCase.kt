package ru.unisafe.shopping_lists.domain

import ru.unisafe.shopping_lists.domain.repositories.ShoppingListsRepository
import javax.inject.Inject

class CreateShoppingListUseCase @Inject constructor(
    private val shoppingListsRepository: ShoppingListsRepository
) {

    suspend fun createShoppingList(name: String) {
        shoppingListsRepository.createShoppingList(name)
    }

}