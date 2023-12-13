package ru.unisafe.shopping_lists.domain

import ru.unisafe.shopping_lists.domain.repositories.KeyRepository
import javax.inject.Inject

class GetCurrentKeyUseCase @Inject constructor(
    private val keyRepository: KeyRepository
) {

    suspend fun getCurrentKey(): String = keyRepository.getCurrentKey()

}