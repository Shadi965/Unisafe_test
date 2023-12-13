package ru.unisafe.example.navigation.shopping_lists.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unisafe.example.navigation.shopping_lists.ShoppingListsRouterImpl
import ru.unisafe.shopping_lists.presentation.ShoppingListsRouter

@Module
@InstallIn(SingletonComponent::class)
interface ShoppingListsRouterModule {

    @Binds
    fun bindShoppingListsRouter(
        shoppingListsRouter: ShoppingListsRouterImpl
    ): ShoppingListsRouter

}