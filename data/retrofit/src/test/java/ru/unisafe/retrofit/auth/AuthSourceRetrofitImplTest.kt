package ru.unisafe.retrofit.auth

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AuthSourceRetrofitImplTest {

    @Test
    fun getNewKeyReturnedTheKeyGeneratedByAuthAPI() = runBlocking {
        val authAPI = AuthAPITestStub()
        val authSource = AuthSourceRetrofitImpl(authAPI)

        val keyFromSource = authSource.getNewKey()
        val realGeneratedKey = authAPI.createdKey

        assertEquals(realGeneratedKey, keyFromSource)
    }

    @Test
    fun verifyKeyCallWithCorrectKeyExpectedTrue() = runBlocking {
        val authAPI = AuthAPITestStub(isKeyCorrect = true)
        val authSource = AuthSourceRetrofitImpl(authAPI)

        val result = authSource.verifyKey("CORRECT")

        assertTrue(result)
    }

    @Test
    fun verifyKeyCallWithIncorrectKeyExpectedFalse() = runBlocking {
        val authAPI = AuthAPITestStub(isKeyCorrect = false)
        val authSource = AuthSourceRetrofitImpl(authAPI)

        val result = authSource.verifyKey("INCORRECT")

        assertFalse(result)
    }

}