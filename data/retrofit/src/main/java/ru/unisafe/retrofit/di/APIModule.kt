package ru.unisafe.retrofit.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.unisafe.retrofit.auth.AuthAPI
import ru.unisafe.retrofit.products.ProductsAPI
import ru.unisafe.retrofit.shoping_lists.ShoppingListsAPI
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class APIModule {

    @Provides
    @Singleton
    fun provideAuthAPI(retrofit: Retrofit): AuthAPI {
        return retrofit.create(AuthAPI::class.java)
    }

    @Provides
    fun provideShoppingListAPI(retrofit: Retrofit): ShoppingListsAPI {
        return retrofit.create(ShoppingListsAPI::class.java)
    }

    @Provides
    fun provideProductsAPI(retrofit: Retrofit): ProductsAPI {
        return retrofit.create(ProductsAPI::class.java)
    }

}