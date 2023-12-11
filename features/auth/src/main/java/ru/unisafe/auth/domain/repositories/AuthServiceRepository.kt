package ru.unisafe.auth.domain.repositories

interface AuthServiceRepository {

    suspend fun getNewKey(): String

    suspend fun verifyKey(key: String): Boolean

}