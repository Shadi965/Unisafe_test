package ru.unisafe.data

import ru.unisafe.data.auth.AuthDataRepository
import ru.unisafe.data.auth.AuthSource
import ru.unisafe.data.auth.KeyDataRepository
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