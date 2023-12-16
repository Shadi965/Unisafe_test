package ru.unisafe.retrofit.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unisafe.data_common.auth.AuthSource
import ru.unisafe.data_common.products.ProductsSource
import ru.unisafe.data_common.shopping_lists.ShoppingListsSource
import ru.unisafe.retrofit.auth.AuthSourceRetrofitImpl
import ru.unisafe.retrofit.products.ProductsSourceRetrofitImpl
import ru.unisafe.retrofit.shopping_lists.ShoppingListsSourceRetrofitImpl

@Module
@InstallIn(SingletonComponent::class)
interface SourceModule {

    @Binds
    fun bindAuthSource(
        authSource: AuthSourceRetrofitImpl
    ): AuthSource

    @Binds
    fun bindShoppingListsSource(
        shoppingListsSource: ShoppingListsSourceRetrofitImpl
    ): ShoppingListsSource

    @Binds
    fun bindProductsSource(
        productsSource: ProductsSourceRetrofitImpl
    ): ProductsSource

}