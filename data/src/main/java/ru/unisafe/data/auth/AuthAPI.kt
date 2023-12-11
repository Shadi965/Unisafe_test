package ru.unisafe.data.auth

import okhttp3.ResponseBody
import retrofit2.http.POST
import retrofit2.http.Query
import ru.unisafe.data.auth.entities.AuthStatus
import ru.unisafe.data.network.AUTHENTICATION
import ru.unisafe.data.network.CREATE_TEST_KEY

interface AuthAPI {

    @POST(CREATE_TEST_KEY)
    suspend fun createTestKey(): ResponseBody

    @POST(AUTHENTICATION)
    suspend fun authentication(@Query("key") key: String)

}