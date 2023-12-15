package ru.unisafe.data.products.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unisafe.data.products.ProductsSource
import ru.unisafe.data.retrofit.ProductsSourceRetrofitImpl

@Module
@InstallIn(SingletonComponent::class)
interface ProductsSourceModule {

    @Binds
    fun bindProductsSource(
        productsSource: ProductsSourceRetrofitImpl
    ): ProductsSource

}