package ru.unisafe.data.auth

interface AuthDataRepository {

    suspend fun getNewKey(): String

    suspend fun verifyKey(key: String): Boolean

    suspend fun logout()

}