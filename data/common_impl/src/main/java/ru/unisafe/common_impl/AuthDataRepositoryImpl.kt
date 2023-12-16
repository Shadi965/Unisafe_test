package ru.unisafe.common_impl

import ru.unisafe.data_common.auth.AuthDataRepository
import ru.unisafe.data_common.auth.AuthSource
import ru.unisafe.data_common.auth.KeyDataRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthDataRepositoryImpl @Inject constructor(
    private val authSource: AuthSource,
    private val keyDataRepository: KeyDataRepository
) : AuthDataRepository {

    override suspend fun getNewKey(): String =
        authSource.getNewKey()

    override suspend fun verifyKey(key: String): Boolean =
        authSource.verifyKey(key)

    override suspend fun logout() {
        keyDataRepository.removeKey()
    }
}