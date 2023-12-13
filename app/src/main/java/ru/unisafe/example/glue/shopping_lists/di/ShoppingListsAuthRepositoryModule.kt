package ru.unisafe.example.glue.shopping_lists.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unisafe.example.glue.shopping_lists.ShoppingListsAuthIntermediateRepositoryImpl
import ru.unisafe.shopping_lists.domain.repositories.ShoppingListsAuthRepository

@Module
@InstallIn(SingletonComponent::class)
interface ShoppingListsAuthRepositoryModule {

    @Binds
    fun bindShoppingListsAuthRepository(
        shoppingListsAuthRepository: ShoppingListsAuthIntermediateRepositoryImpl
    ): ShoppingListsAuthRepository

}