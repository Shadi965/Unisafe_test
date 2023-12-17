package ru.unisafe.common_impl

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class KeyDataRepositorySharedPreferencesImplTest {

    companion object {
        const val FILE_NAME = KeyDataRepositorySharedPreferencesImpl.FILE_NAME
        const val LAST_AUTH_KEY = KeyDataRepositorySharedPreferencesImpl.LAST_AUTH_KEY
    }

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val sharedPreferences = appContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    private val sharedPreferencesKey: String?
        get() = sharedPreferences.getString(LAST_AUTH_KEY, null)

    @After
    fun clearSharedPreferences() {
        sharedPreferences.edit().clear().apply()
    }

    @Test
    fun saveKeyCallExpectedSavedKey() = runBlocking {
        val keyRepository = KeyDataRepositorySharedPreferencesImpl(appContext)

        keyRepository.saveKey("TEST")
        val key = sharedPreferencesKey

        assertEquals("TEST", key)
    }

    @Test
    fun saveKeyMultipleCallsExpectedLastSavedKey() = runBlocking {
        val keyRepository = KeyDataRepositorySharedPreferencesImpl(appContext)

        keyRepository.saveKey("TEST_1")
        keyRepository.saveKey("TEST_2")
        val key = sharedPreferencesKey

        assertEquals("TEST_2", key)
    }

    @Test
    fun getLastKeyCallWhenTheKeyDoesNotExistExpectedNull() = runBlocking {
        val keyRepository = KeyDataRepositorySharedPreferencesImpl(appContext)

        val lastKey = keyRepository.getLastKey()

        assertNull(lastKey)
    }

    @Test
    fun getLastKeyAfterSaveKeyCallExpectedSavedKey() = runBlocking {
        val keyRepository = KeyDataRepositorySharedPreferencesImpl(appContext)

        keyRepository.saveKey("TEST")
        val lastKey = keyRepository.getLastKey()

        assertEquals("TEST", lastKey)

    }

    @Test
    fun getLastKeyCallBeforeAndAfterSaveKeyCallExpectedThatResultChanged() = runBlocking {
        val keyRepository = KeyDataRepositorySharedPreferencesImpl(appContext)

        keyRepository.saveKey("TEST_1")
        val firstCall = keyRepository.getLastKey()
        keyRepository.saveKey("TEST_2")
        val secondCall = keyRepository.getLastKey()

        assertNotEquals(firstCall, secondCall)
    }

    @Test
    fun getLastKeyMultipleCallsExpectedTheSameResult() = runBlocking {
        val keyRepository = KeyDataRepositorySharedPreferencesImpl(appContext)

        keyRepository.saveKey("TEST")
        val firstCall = keyRepository.getLastKey()
        val secondCall = keyRepository.getLastKey()

        assertEquals(firstCall, secondCall)

    }

    @Test
    fun removeKeyWhenTheKeyDoesNotExistExpectedNothing() = runBlocking {
        val keyRepository = KeyDataRepositorySharedPreferencesImpl(appContext)

        val keyBefore = sharedPreferencesKey
        keyRepository.removeKey()
        val keyAfter = sharedPreferencesKey

        assertNull(keyBefore)
        assertEquals(keyBefore, keyAfter)
    }

    @Test
    fun removeKeyAfterSaveKeyCallExpectedKeyRemoved() = runBlocking {
        val keyRepository = KeyDataRepositorySharedPreferencesImpl(appContext)

        keyRepository.saveKey("TEST")
        keyRepository.removeKey()
        val key = sharedPreferencesKey

        assertNull(key)
    }

    @Test
    fun getLastKeyAfterRemoveKeyCallExpectedNull() = runBlocking {
        val keyRepository = KeyDataRepositorySharedPreferencesImpl(appContext)

        keyRepository.saveKey("TEST")
        keyRepository.removeKey()
        val lastKey = keyRepository.getLastKey()

        assertNull(lastKey)
    }

    @Test
    fun saveKeyAfterRemoveKeyCallExpectedSavedKey() = runBlocking {
        val keyRepository = KeyDataRepositorySharedPreferencesImpl(appContext)

        keyRepository.saveKey("TEST_1")
        keyRepository.removeKey()
        keyRepository.saveKey("TEST_2")
        val key = sharedPreferencesKey

        assertEquals("TEST_2", key)
    }

}