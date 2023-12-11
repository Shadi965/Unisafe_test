package ru.unisafe.data.products.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.unisafe.data.products.ProductsAPI

@Module
@InstallIn(SingletonComponent::class)
class ProductsAPI_Module {

    @Provides
    fun provideProductsAPI(retrofit: Retrofit): ProductsAPI {
        return retrofit.create(ProductsAPI::class.java)
    }

}