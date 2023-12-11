package ru.unisafe.auth.domain.repositories

interface KeyRepository {

    suspend fun saveKey(key: String)

    suspend fun getDefaultKey(): String

}