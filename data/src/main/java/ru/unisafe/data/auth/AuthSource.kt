package ru.unisafe.data.auth

interface AuthSource {

    suspend fun getNewKey(): String

    suspend fun verifyKey(key: String): Boolean

}