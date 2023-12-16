package ru.unisafe.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unisafe.data.room.ShoppingListsRepositoryRoomImpl
import ru.unisafe.data_common.shopping_lists.ShoppingListsDataRepository

@Module
@InstallIn(SingletonComponent::class)
interface ShoppingListsRepositoryModule {

    @Binds
    fun bindShoppingListsDataRepository(
        shoppingListsDataRepository: ShoppingListsRepositoryRoomImpl
    ): ShoppingListsDataRepository

}