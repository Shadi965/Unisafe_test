package ru.unisafe.shopping_lists.domain.repositories

interface AuthRepository {

    suspend fun logout()

}