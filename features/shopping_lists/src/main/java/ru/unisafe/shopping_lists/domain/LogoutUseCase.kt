package ru.unisafe.shopping_lists.domain

import ru.unisafe.shopping_lists.domain.repositories.ShoppingListsAuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val shoppingListsAuthRepository: ShoppingListsAuthRepository
) {

    suspend fun logout() {
        shoppingListsAuthRepository.logout()
    }

}