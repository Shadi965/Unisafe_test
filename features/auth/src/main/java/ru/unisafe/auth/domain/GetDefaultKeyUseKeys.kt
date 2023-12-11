package ru.unisafe.auth.domain

import ru.unisafe.auth.domain.repositories.KeyRepository
import javax.inject.Inject

class GetDefaultKeyUseKeys @Inject constructor(
    private val keyRepository: KeyRepository
) {

    suspend fun getDefaultKey(): String {
        return keyRepository.getDefaultKey()
    }

}