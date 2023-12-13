package ru.unisafe.example.glue.shopping_lists.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unisafe.example.glue.shopping_lists.ShoppingListsKeyIntermediateRepositoryImpl
import ru.unisafe.shopping_lists.domain.repositories.ShoppingListsKeyRepository

@Module
@InstallIn(SingletonComponent::class)
interface ShoppingListsKeyRepositoryModule {

    @Binds
    fun bindShoppingListsKeyRepository(
        shoppingListsKeyRepository: ShoppingListsKeyIntermediateRepositoryImpl
    ): ShoppingListsKeyRepository

}