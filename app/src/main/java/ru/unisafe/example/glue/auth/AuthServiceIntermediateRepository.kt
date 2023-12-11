package ru.unisafe.example.glue.auth

import ru.unisafe.auth.domain.repositories.AuthServiceRepository
import ru.unisafe.data.AuthDataRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthServiceIntermediateRepository @Inject constructor(
    private val authDataRepository: AuthDataRepository
): AuthServiceRepository {
    override suspend fun getNewKey(): String {
        return authDataRepository.getNewKey()
    }

    override suspend fun verifyKey(key: String): Boolean {
        return authDataRepository.verifyKey(key)
    }
}