package ru.unisafe.data.auth.source

interface AuthSource {

    suspend fun getNewKey(): String

    suspend fun verifyKey(key: String): Boolean

}