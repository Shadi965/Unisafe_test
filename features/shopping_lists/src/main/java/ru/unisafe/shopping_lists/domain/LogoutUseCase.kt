package ru.unisafe.shopping_lists.domain

import ru.unisafe.shopping_lists.domain.repositories.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend fun logout() {
        authRepository.logout()
    }

}