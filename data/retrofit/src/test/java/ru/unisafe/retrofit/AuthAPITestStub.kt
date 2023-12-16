package ru.unisafe.retrofit

import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import ru.unisafe.retrofit.auth.AuthAPI
import ru.unisafe.retrofit.auth.entities.AuthResponse

class AuthAPITestStub(private val isKeyCorrect: Boolean = true) : AuthAPI {

    var createdKey: String? = null

    private val keys = mutableListOf(
        "92EGHS",
        "AOFK23",
        "93LDWO",
        "J38H12",
        "PQJS11"
    )

    override suspend fun createTestKey(): ResponseBody {
        keys.shuffle()
        createdKey = keys[0]
        return ResponseBody.create(MediaType.get("text/plain"), createdKey!!)
    }

    override suspend fun authentication(key: String): AuthResponse {
        if (isKeyCorrect) return AuthResponse(true, null)
        else throw HttpException(
            Response.error<Any>(
                406, ResponseBody.create(MediaType.get("text/plain"), "")
            )
        )
    }
}