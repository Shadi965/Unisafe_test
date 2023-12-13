package ru.unisafe.data.shopping_lists.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unisafe.data.shopping_lists.source.ShoppingListsSource
import ru.unisafe.data.shopping_lists.source.ShoppingListsSourceRetrofitImpl

@Module
@InstallIn(SingletonComponent::class)
interface ShoppingListsSourceModule {

    @Binds
    fun bindShoppingListsSource(
        shoppingListsSource: ShoppingListsSourceRetrofitImpl
    ): ShoppingListsSource

}