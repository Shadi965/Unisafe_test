package ru.unisafe.example.glue.shopping_lists

import ru.unisafe.data_common.auth.KeyDataRepository
import ru.unisafe.shopping_lists.domain.repositories.ShoppingListsKeyRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingListsKeyIntermediateRepositoryImpl @Inject constructor(
    private val keyDataRepository: KeyDataRepository
) : ShoppingListsKeyRepository {
    override suspend fun getCurrentKey(): String {
        return keyDataRepository.getLastKey() ?: throw IllegalStateException()
    }

}