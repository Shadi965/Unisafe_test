package ru.unisafe.data.retrofit

import retrofit2.HttpException
import ru.unisafe.data.auth.AuthSource
import ru.unisafe.data.retrofit.auth.AuthAPI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthSourceRetrofitImpl @Inject constructor(
    private val authAPI: AuthAPI

): AuthSource {
    override suspend fun getNewKey(): String = authAPI.createTestKey().string()

    override suspend fun verifyKey(key: String): Boolean {
        return try {
            authAPI.authentication(key)
            true
        } catch (ex: HttpException) {
            if (ex.code() == 406) {
                false
            } else
                throw ex
        }
    }
}