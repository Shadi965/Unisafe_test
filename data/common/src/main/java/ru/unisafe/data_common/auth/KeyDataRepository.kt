package ru.unisafe.data_common.auth

interface KeyDataRepository {

    fun getDefaultKey(): String = "92EGHS"

    suspend fun getLastKey(): String?

    suspend fun saveKey(key: String)

    suspend fun removeKey()

}