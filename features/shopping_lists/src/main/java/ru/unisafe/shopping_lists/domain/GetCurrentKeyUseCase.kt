package ru.unisafe.shopping_lists.domain

import ru.unisafe.shopping_lists.domain.repositories.ShoppingListsKeyRepository
import javax.inject.Inject

class GetCurrentKeyUseCase @Inject constructor(
    private val ShoppingListsKeyRepository: ShoppingListsKeyRepository
) {

    suspend fun getCurrentKey(): String = ShoppingListsKeyRepository.getCurrentKey()

}