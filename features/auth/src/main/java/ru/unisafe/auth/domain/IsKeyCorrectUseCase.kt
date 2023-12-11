package ru.unisafe.auth.domain

import ru.unisafe.auth.domain.repositories.AuthServiceRepository
import javax.inject.Inject

class IsKeyCorrectUseCase @Inject constructor(
    private val authServiceRepository: AuthServiceRepository
) {

    suspend fun isKeyCorrect(key: String): Boolean {
        return authServiceRepository.verifyKey(key)
    }

}