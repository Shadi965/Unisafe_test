package ru.unisafe.data

interface KeyDataRepository {

    fun getDefaultKey(): String

    fun saveKey(key: String)

    fun removeKey()

}