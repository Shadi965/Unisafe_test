package ru.unisafe.auth.domain

import ru.unisafe.auth.domain.repositories.AuthServiceRepository
import javax.inject.Inject

class GetNewKeyUseCase @Inject constructor(
    private val authServiceRepository: AuthServiceRepository
) {

    suspend fun getNewKey(): String {
        return authServiceRepository.getNewKey()
    }

}