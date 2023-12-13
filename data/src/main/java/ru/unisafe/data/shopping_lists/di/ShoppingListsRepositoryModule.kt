package ru.unisafe.data.shopping_lists.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unisafe.data.shopping_lists.ShoppingListsDataRepository
import ru.unisafe.data.room.ShoppingListsRepositoryRoomImpl

@Module
@InstallIn(SingletonComponent::class)
interface ShoppingListsDataRepositoryModule {

    @Binds
    fun bindShoppingListsDataRepository(
        shoppingListsDataRepository: ShoppingListsRepositoryRoomImpl
    ): ShoppingListsDataRepository

}