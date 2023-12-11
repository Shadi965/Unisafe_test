package ru.unisafe.data

interface AuthDataRepository {

    suspend fun getNewKey(): String

    suspend fun verifyKey(key: String): Boolean

}