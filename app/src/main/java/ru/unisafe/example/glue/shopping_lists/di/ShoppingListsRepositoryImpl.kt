package ru.unisafe.example.glue.shopping_lists.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unisafe.example.glue.shopping_lists.ShoppingListsIntermediateRepositoryImpl
import ru.unisafe.shopping_lists.domain.repositories.ShoppingListsRepository

@Module
@InstallIn(SingletonComponent::class)
interface ShoppingListsRepositoryImpl {

    @Binds
    fun bindShoppingListsRepository(
        shoppingListsRepository: ShoppingListsIntermediateRepositoryImpl
    ): ShoppingListsRepository

}