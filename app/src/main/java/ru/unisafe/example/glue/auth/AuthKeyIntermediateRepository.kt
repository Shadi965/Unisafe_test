package ru.unisafe.example.glue.auth

import ru.unisafe.auth.domain.repositories.KeyRepository
import ru.unisafe.data.auth.KeyDataRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthKeyIntermediateRepository @Inject constructor(
    private val keyDataRepository: KeyDataRepository
): KeyRepository {
    override suspend fun saveKey(key: String) {
        keyDataRepository.saveKey(key)
    }

    override suspend fun getDefaultKey(): String {
        return keyDataRepository.getDefaultKey()
    }
}