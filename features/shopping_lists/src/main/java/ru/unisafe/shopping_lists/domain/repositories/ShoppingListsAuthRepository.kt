package ru.unisafe.shopping_lists.domain.repositories

interface ShoppingListsAuthRepository {

    suspend fun logout()

}