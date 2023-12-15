package ru.unisafe.example.glue.products.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unisafe.example.glue.products.ProductsRepositoryIntermediateImpl
import ru.unisafe.products.domain.repositories.ProductsRepository

@Module
@InstallIn(SingletonComponent::class)
interface ProductsRepositoryModule {

    @Binds
    fun bindProductRepository(
        productsRepository: ProductsRepositoryIntermediateImpl
    ): ProductsRepository

}