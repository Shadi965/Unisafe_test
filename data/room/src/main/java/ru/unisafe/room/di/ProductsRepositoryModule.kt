package ru.unisafe.room.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unisafe.data_common.products.ProductsDataRepository
import ru.unisafe.room.ProductsRepositoryRoomImpl

@Module
@InstallIn(SingletonComponent::class)
interface ProductsRepositoryModule {

    @Binds
    fun bindProductsRepository(
        productsRepository: ProductsRepositoryRoomImpl
    ): ProductsDataRepository

}