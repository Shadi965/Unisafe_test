package ru.unisafe.shopping_lists.domain.repositories

interface ShoppingListsKeyRepository {

    suspend fun getCurrentKey(): String

}