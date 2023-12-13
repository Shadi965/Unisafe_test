package ru.unisafe.data.shared_preferences

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.unisafe.data.auth.KeyDataRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KeyDataRepositorySharedPreferencesImpl @Inject constructor(
    @ApplicationContext applicationContext: Context
) : KeyDataRepository {

    private var currentKey: String? = null

    private val sharedPreferences = applicationContext.getSharedPreferences("settings", Context.MODE_PRIVATE)

    override fun getDefaultKey(): String {
        return super.getDefaultKey()
    }

    override suspend fun getLastKey(): String? {
        if (currentKey == null)
            currentKey = sharedPreferences.getString(LAST_AUTH_KEY, null)
        return currentKey
    }

    override suspend fun saveKey(key: String) {
        sharedPreferences.edit().putString(LAST_AUTH_KEY, key).apply()
        currentKey = key
    }

    override suspend fun removeKey() {
        currentKey = null
        sharedPreferences.edit().remove(LAST_AUTH_KEY).apply()
    }

    companion object {
        const val LAST_AUTH_KEY = "last_auth_key"
    }

}