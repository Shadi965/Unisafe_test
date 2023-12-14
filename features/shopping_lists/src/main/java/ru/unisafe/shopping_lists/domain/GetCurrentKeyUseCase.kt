package ru.unisafe.shopping_lists.domain

import ru.unisafe.shopping_lists.domain.repositories.ShoppingListsKeyRepository
import javax.inject.Inject

class GetCurrentKeyUseCase @Inject constructor(
    private val shoppingListsKeyRepository: ShoppingListsKeyRepository
) {

    suspend fun getCurrentKey(): String = shoppingListsKeyRepository.getCurrentKey()

}