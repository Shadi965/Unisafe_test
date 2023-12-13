package ru.unisafe.shopping_lists.domain.repositories

interface KeyRepository {

    suspend fun getCurrentKey(): String

}