package ru.unisafe.data_common.auth

interface AuthSource {

    suspend fun getNewKey(): String

    suspend fun verifyKey(key: String): Boolean

}