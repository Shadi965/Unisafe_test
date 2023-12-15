package ru.unisafe.example.navigation.products.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unisafe.example.navigation.products.ProductsRouterImpl
import ru.unisafe.products.presentation.ProductsRouter

@Module
@InstallIn(SingletonComponent::class)
interface ProductsRouterModule {

    @Binds
    fun bindProductsRouter(
        productsRouter: ProductsRouterImpl
    ): ProductsRouter

}