package ru.unisafe.data.shopping_lists.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.unisafe.data.shopping_lists.ShoppingListsAPI

@Module
@InstallIn(SingletonComponent::class)
class ShoppingListsAPI_Module {

    @Provides
    fun provideShoppingListAPI(retrofit: Retrofit): ShoppingListsAPI {
        return retrofit.create(ShoppingListsAPI::class.java)
    }

}