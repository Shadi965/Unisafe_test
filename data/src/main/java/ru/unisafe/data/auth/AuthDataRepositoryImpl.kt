package ru.unisafe.data.auth

import ru.unisafe.data.AuthDataRepository
import ru.unisafe.data.KeyDataRepository
import ru.unisafe.data.auth.source.AuthSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthDataRepositoryImpl @Inject constructor(
    private val authSource: AuthSource,
    private val keyDataRepository: KeyDataRepository
) : AuthDataRepository {

    override suspend fun getNewKey(): String {
        return authSource.getNewKey()
    }

    override suspend fun verifyKey(key: String): Boolean {
        return authSource.verifyKey(key)
    }

    override suspend fun logout() {
        keyDataRepository.removeKey()
    }
}