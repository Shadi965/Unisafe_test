package ru.unisafe.auth.domain

import ru.unisafe.auth.domain.repositories.KeyRepository
import javax.inject.Inject

class SaveKeyUseCase @Inject constructor(
    private val keyRepository: KeyRepository
) {

    suspend fun saveKey(key: String) {
        keyRepository.saveKey(key)
    }

}