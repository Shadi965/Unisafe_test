package ru.unisafe.data.products.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unisafe.data.products.ProductsDataRepository
import ru.unisafe.data.room.ProductsRepositoryRoomImpl

@Module
@InstallIn(SingletonComponent::class)
interface ProductsRepositoryModule {

    @Binds
    fun bindProductsRepository(
        productsRepository: ProductsRepositoryRoomImpl
    ): ProductsDataRepository

}